package guru.stefma.cleancomponents.usecase.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
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

    private val coroutineScope = TestCoroutineScope()

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
    fun `default coroutineusecase`() {
        val useCaseImpl = DefaultCoroutineUseCase()

        lateinit var result: String
        coroutineScope.runBlockingTest {
            useCaseImpl(Unit,
                onSuccess = { result = it },
                onFailure = { fail(it) })
        }
        assertThat(result).isEqualTo(successResult)
    }

    inner class DefaultCoroutineUseCase : CoroutineUseCase<String, Unit> {

        override suspend fun buildUseCase(params: Unit): Result<String> {
            return Result.success(successResult)
        }
    }
}