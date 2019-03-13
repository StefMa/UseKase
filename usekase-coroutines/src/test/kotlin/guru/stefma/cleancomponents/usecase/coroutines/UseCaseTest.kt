package guru.stefma.cleancomponents.usecase.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@ObsoleteCoroutinesApi
class UseCaseTest {

    private val testContext = TestCoroutineContext()

    @Test
    fun `default coroutineusecase`() {
        val useCaseImpl = DefaultCoroutineUseCase()
        val scope = CoroutineScope(testContext)

        var value = ""
        scope.launch {
            value = useCaseImpl.execute(Unit)
        }

        testContext.triggerActions()
        assertThat(value).isEqualTo("Value")
    }

    @Test
    fun `default coroutineusecase with delay`() {
        val delay: Long = 500
        val useCaseImpl = DefaultCoroutineUseCase(delay)
        val scope = CoroutineScope(testContext)

        var value = ""
        scope.launch {
            value = useCaseImpl.execute(Unit)
        }

        testContext.triggerActions()
        // No value - because the execution take longer
        assertThat(value).isEqualTo("")
    }

    @Test
    fun `default coroutineusecase with delay successfully`() {
        val delay: Long = 500
        val useCaseImpl = DefaultCoroutineUseCase(delay)
        val scope = CoroutineScope(testContext)

        var value = ""
        scope.launch {
            value = useCaseImpl.execute(Unit)
        }

        testContext.advanceTimeBy(500)
        testContext.triggerActions()
        assertThat(value).isEqualTo("Value")
    }

    class DefaultCoroutineUseCase(private val delay: Long = 0) : CoroutineUseCase<String, Unit> {

        override suspend fun execute(params: Unit): String {
            delay(delay)
            return "Value"
        }
    }

}