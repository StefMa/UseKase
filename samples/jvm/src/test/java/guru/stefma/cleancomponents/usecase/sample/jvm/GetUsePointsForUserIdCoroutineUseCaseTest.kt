package guru.stefma.cleancomponents.usecase.sample.jvm

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import guru.stefma.cleancomponents.usecase.sample.jvm.Gender.FEMALE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.*

@ExperimentalCoroutinesApi
class GetUsePointsForUserIdCoroutineUseCaseTest {

    private val coroutineScope = TestCoroutineScope()

    private val mockGetUser = mock<GetUserCoroutine>()

    @Test
    fun `test get points successfully`() {
        val user = User("id", "Name", FEMALE, "anyKey")
        coroutineScope.launch { whenever(mockGetUser.execute(any())) doReturn Result.success(user) }
        val useCase = GetUsePointsForUserIdCoroutineUseCase(mockGetUser)
        var points: Int? = null

        coroutineScope.launch {
            points = useCase.execute(GetUsePointsForUserIdCoroutineUseCase.Params("userId")).getOrNull()
        }

        assertThat(points).isEqualTo(99)
    }

    @Test
    fun `test get points on error`() {
        coroutineScope.launch { whenever(mockGetUser.execute(any())) doThrow Throwable() }
        val useCase = GetUsePointsForUserIdCoroutineUseCase(mockGetUser)
        var points: Int? = null

        coroutineScope.launch {
            points = useCase.execute(GetUsePointsForUserIdCoroutineUseCase.Params("userId")).getOrNull()
        }

        assertThat(coroutineScope.uncaughtExceptions[0]).isInstanceOf(Throwable::class.java)
        assertThat(points).isNull()
    }
}