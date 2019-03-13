package guru.stefma.cleancomponents.usecase.sample.jvm

import com.nhaarman.mockitokotlin2.*
import guru.stefma.cleancomponents.usecase.sample.jvm.Gender.FEMALE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@ObsoleteCoroutinesApi
class GetUsePointsForUserIdCoroutineUseCaseTest {

    private val testContext = TestCoroutineContext()

    private val mockGetUser = mock<GetUserCoroutine>()

    private val coroutineScope = CoroutineScope(testContext)

    @Test
    fun `test get points successfully`() {
        val user = User("id", "Name", FEMALE, "anyKey")
        coroutineScope.launch { whenever(mockGetUser.execute(any())) doReturn user }
        val useCase = GetUsePointsForUserIdCoroutineUseCase(mockGetUser)
        var points: Int? = null

        coroutineScope.launch {
            points = useCase.execute(GetUsePointsForUserIdCoroutineUseCase.Params("userId"))
        }

        testContext.triggerActions()
        assertThat(points).isEqualTo(99)
    }

    @Test
    fun `test get points on error`() {
        coroutineScope.launch { whenever(mockGetUser.execute(any())) doThrow Throwable() }
        val useCase = GetUsePointsForUserIdCoroutineUseCase(mockGetUser)
        var points: Int? = null

        coroutineScope.launch {
            points = useCase.execute(GetUsePointsForUserIdCoroutineUseCase.Params("userId"))
        }

        testContext.triggerActions()
        assertThat(testContext.exceptions[0]).isInstanceOf(Throwable::class.java)
        assertThat(points).isNull()
    }
}