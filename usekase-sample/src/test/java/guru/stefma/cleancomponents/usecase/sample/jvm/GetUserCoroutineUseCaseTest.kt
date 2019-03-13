package guru.stefma.cleancomponents.usecase.sample.jvm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@ObsoleteCoroutinesApi
class GetUserCoroutineUseCaseTest {

    private val testContext = TestCoroutineContext()

    private val useCase = GetUserCoroutineUseCase()

    @Test
    fun `test get user`() {
        var user: User? = null

        CoroutineScope(testContext).launch {
            user = useCase.execute(GetUserCoroutineUseCase.Params("userId"))
        }

        testContext.triggerActions()
        assertThat(user).isNotNull()
        user!!.apply {
            assertThat(id).isEqualTo("userId")
            assertThat(name).isEqualTo("Thorsten")
            assertThat(gender).isEqualTo(Gender.MALE)
        }
    }
}