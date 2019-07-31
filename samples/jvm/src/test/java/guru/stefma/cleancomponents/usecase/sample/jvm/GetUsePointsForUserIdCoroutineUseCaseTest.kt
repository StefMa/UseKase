package guru.stefma.cleancomponents.usecase.sample.jvm

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import guru.stefma.cleancomponents.usecase.sample.jvm.Gender.FEMALE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.*

@ExperimentalCoroutinesApi
class GetUsePointsForUserIdCoroutineUseCaseTest {

    private val coroutineScope = TestCoroutineScope()

    private val mockGetUser = mock<GetUserCoroutine>()

    @Test
    fun `test get points successfully`() {
        val user = User("id", "Name", FEMALE, "anyKey")
        coroutineScope.runBlockingTest { whenever(mockGetUser.buildUseCase(any())) doReturn Result.success(user) }
        val useCase = GetUsePointsForUserIdCoroutineUseCase(mockGetUser)
        var points: Int? = null

        coroutineScope.runBlockingTest {
            useCase(GetUsePointsForUserIdCoroutineUseCase.Params("userId"), {
                points = it
            }, {})
        }

        assertThat(points).isEqualTo(99)
    }

    @Test
    fun `test get points on error`() {
        val throwable = Throwable()
        coroutineScope.runBlockingTest { whenever(mockGetUser.buildUseCase(any())) doReturn Result.failure(throwable) }
        val useCase = GetUsePointsForUserIdCoroutineUseCase(mockGetUser)
        var points: Int? = null
        var exception: Throwable? = null

        coroutineScope.runBlockingTest {
            useCase(GetUsePointsForUserIdCoroutineUseCase.Params("userId"), {
                points = it
            }, {
                exception = it
            })
        }

        assertThat(exception).isEqualTo(throwable)
        assertThat(points).isNull()
    }
}