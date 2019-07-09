package guru.stefma.cleancomponents.usecase.sample.jvm

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.*

@ExperimentalCoroutinesApi
class GetUserCoroutineUseCaseTest {

    private val testContext = TestCoroutineScope()

    private val useCase = GetUserCoroutineUseCase()

    @Test
    fun `test get user`() {
        var user: User? = null

        testContext.launch {
            user = useCase.execute(GetUserCoroutineUseCase.Params("userId")).getOrNull()
        }

        assertThat(user).isNotNull
        user!!.apply {
            assertThat(id).isEqualTo("userId")
            assertThat(name).isEqualTo("Thorsten")
            assertThat(gender).isEqualTo(Gender.MALE)
        }
    }
}