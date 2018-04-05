package guru.stefma.cleancomponents.usecase.sample.jvm

import guru.stefma.cleancomponents.usecase.sample.jvm.GetUserUseCase.Params
import io.reactivex.schedulers.TestScheduler
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.*

class GetUserUseCaseTest {

    private val testScheduler = TestScheduler()

    private val useCase = GetUserUseCase(testScheduler, testScheduler)

    @Test
    fun `test get user`() {
        val testObserver = useCase.execute(Params("userId")).test()

        testScheduler.triggerActions()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        val user = testObserver.values()[0]
        assertThat(user.id).isEqualTo("userId")
        assertThat(user.name).isEqualTo("Thorsten")
        assertThat(user.gender).isEqualTo(Gender.MALE)
    }
}