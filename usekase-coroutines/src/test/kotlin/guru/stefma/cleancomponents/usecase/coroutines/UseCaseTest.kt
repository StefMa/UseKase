package guru.stefma.cleancomponents.usecase.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.*
import java.util.concurrent.Executors

@ExperimentalCoroutinesApi
class UseCaseTest {

    private val successResult = "Value"

    private val mainThreadSurrogate = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `default coroutineusecase`() = runBlockingTest {
        val useCaseImpl = DefaultCoroutineUseCase()

        lateinit var result: String
        useCaseImpl(Unit,
            onSuccess = { result = it },
            onFailure = { fail(it) })

        assertThat(result).isEqualTo(successResult)
    }

    inner class DefaultCoroutineUseCase : CoroutineUseCase<String, Unit>() {

        override suspend fun execute(params: Unit): String {
            return successResult
        }
    }
}