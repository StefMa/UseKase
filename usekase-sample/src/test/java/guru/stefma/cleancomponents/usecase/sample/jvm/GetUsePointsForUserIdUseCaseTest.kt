package guru.stefma.cleancomponents.usecase.sample.jvm

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import guru.stefma.cleancomponents.usecase.sample.jvm.Gender.FEMALE
import guru.stefma.cleancomponents.usecase.sample.jvm.GetUsePointsForUserIdUseCase.Params
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.*

class GetUsePointsForUserIdUseCaseTest {

    private val testScheduler = TestScheduler()

    private val mockGetUser = mock<GetUser>()

    @Test
    fun `test get points successfully`() {
        val user = User("id", "Name", FEMALE, "anyKey")
        whenever(mockGetUser.buildUseCase(any())) doReturn Single.just(user)
        val useCase = GetUsePointsForUserIdUseCase(mockGetUser, testScheduler, testScheduler)

        val testObserver = useCase.execute(Params("userId")).test()

        testScheduler.triggerActions()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        val values = testObserver.values()[0]
        assertThat(values).isEqualTo(99)
    }

    @Test
    fun `test get points on error`() {
        whenever(mockGetUser.buildUseCase(any())) doReturn Single.error(Throwable())
        val useCase = GetUsePointsForUserIdUseCase(mockGetUser, testScheduler, testScheduler)

        val testObserver = useCase.execute(Params("userId")).test()

        testScheduler.triggerActions()
        testObserver.assertNotComplete()
        testObserver.assertError(Throwable::class.java)
        testObserver.assertNoValues()
    }
}