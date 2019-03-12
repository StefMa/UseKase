package guru.stefma.cleancomponents.usecase

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.*

@DisplayName("All UseCase tests")
class UseCaseTest {

    @Test
    @DisplayName("UseCase")
    fun `default usecase`() {
        val useCaseImpl = object : UseCase<String, Boolean> {
            override fun buildUseCase(params: Boolean): String {
                return params.toString()
            }
        }

        useCaseImpl.executeAndAssert(true, true.toString())
    }

    private fun UseCase<String, Boolean>.executeAndAssert(param: Boolean, expected: String) {
        val value = execute(param)

        assertThat(value).isEqualTo(expected)
    }

}
