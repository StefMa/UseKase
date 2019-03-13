package guru.stefma.cleancomponents.sample.android.usecase

import com.nhaarman.mockitokotlin2.mock
import guru.stefma.cleancomponents.usecase.UseCase
import guru.stefma.cleancomponents.usecase.rx.CompletableUseCase
import guru.stefma.cleancomponents.usecase.rx.MaybeUseCase
import guru.stefma.cleancomponents.usecase.rx.ObservableUseCase
import guru.stefma.cleancomponents.usecase.rx.SingleUseCase
import io.reactivex.schedulers.TestScheduler
import org.assertj.core.api.Assertions.*
import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

@RunWith(JUnit4::class)
class UseCaseTest {

    private val testScheduler = TestScheduler()

    @Test
    fun `test single use case`() {
        val useCase = TestSingleUseCase(testScheduler, testScheduler)

        val testObserver = useCase.execute(true).test()

        testScheduler.triggerActions()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValue("AString")
    }

    @Test
    fun `test single use case typealias exist`() {
        val typealiasMock = mock<TestSingle>()
        assertThat(typealiasMock).isInstanceOf(SingleUseCase::class.java)
    }

    @Test
    fun `test so proud of me use case`() {
        val useCase = SoProudOfMeSingleUseCase(testScheduler, testScheduler)

        val testObserver = useCase.execute(true).test()

        testScheduler.triggerActions()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        val values = testObserver.values()[0]
        assertThat(values.size).isEqualTo(0)
    }

    @Test
    fun `test so proud of me typealias  exist`() {
        val typealiasMock = mock<SoProudOfMeSingle>()
        assertThat(typealiasMock).isInstanceOf(SingleUseCase::class.java)
    }

    @Test
    fun `test observable use case`() {
        val useCase = TestObservableUseCase(testScheduler, testScheduler)

        val testObserver = useCase.execute(true).test()

        testScheduler.triggerActions()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        val values = testObserver.values()[0]
        assertThat(values.size).isEqualTo(0)
    }

    @Test
    fun `test observable typealias exist`() {
        val typealiasMock = mock<TestObservable>()
        assertThat(typealiasMock).isInstanceOf(ObservableUseCase::class.java)
    }

    @Test
    fun `test maybe use case`() {
        val useCase = TestMaybeUseCase(testScheduler, testScheduler)

        val testObserver = useCase.execute(true).test()

        testScheduler.triggerActions()
        testObserver.assertNotComplete()
        testObserver.assertError(Throwable::class.java)
        testObserver.assertNoValues()
    }

    @Test
    fun `test maybe typealias exist`() {
        val typealiasMock = mock<TestMaybe>()
        assertThat(typealiasMock).isInstanceOf(MaybeUseCase::class.java)
    }

    @Test
    fun `test completable use case`() {
        val useCase = TestCompletableUseCase(testScheduler, testScheduler)

        val testObserver = useCase.execute(emptyList()).test()

        testScheduler.triggerActions()
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertNoValues()
    }

    @Test
    fun `test completable typealias exist`() {
        val typealiasMock = mock<TestCompletable>()
        assertThat(typealiasMock).isInstanceOf(CompletableUseCase::class.java)
    }

    @Test
    fun `test generic use case`() {
        val useCase = TestGenericUseCase()

        val value = useCase.execute(Params(true))

        assertThat(value.size).isEqualTo(1)
        assertThat(value[0]).isEqualTo(true)
    }

    @Test
    fun `test generic typealias exist`() {
        val typealiasMock = mock<TestGeneric>()
        assertThat(typealiasMock).isInstanceOf(UseCase::class.java)
    }
}