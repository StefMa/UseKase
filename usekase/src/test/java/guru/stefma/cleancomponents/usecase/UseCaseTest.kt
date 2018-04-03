package guru.stefma.cleancomponents.usecase

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.*

@DisplayName("All UseCase tests")
class UseCaseTest {

    private val testScheduler = TestScheduler()

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

    @Test
    @DisplayName("RxUseCase")
    fun `default rxusecase`() {
        val useCaseImpl = object : RxUseCase<String, Boolean> {
            override val executionScheduler: Scheduler
                get() = testScheduler

            override val postExecutionScheduler: Scheduler
                get() = testScheduler

            override fun buildUseCase(params: Boolean): String {
                return params.toString()
            }
        }

        useCaseImpl.executeAndAssert(true, true.toString())
    }

    @Test
    @DisplayName("SingleUseCase")
    fun `default singleusecase`() {
        val useCaseImpl = object : SingleUseCase<String, Boolean> {
            override val executionScheduler: Scheduler
                get() = testScheduler

            override val postExecutionScheduler: Scheduler
                get() = testScheduler

            override fun buildUseCase(params: Boolean): Single<String> {
                return Single.just(params.toString())
            }
        }

        useCaseImpl.execute(true).test().assert(true.toString())
    }

    @Test
    @DisplayName("ObservableUseCase")
    fun `default observableusecase`() {
        val useCaseImpl = object : ObservableUseCase<String, Boolean> {
            override val executionScheduler: Scheduler
                get() = testScheduler

            override val postExecutionScheduler: Scheduler
                get() = testScheduler

            override fun buildUseCase(params: Boolean): Observable<String> {
                return Observable.just(params.toString())
            }
        }

        useCaseImpl.execute(true).test().assert(true.toString())
    }

    @Test
    @DisplayName("CompletableUseCase")
    fun `default completableusecase`() {
        val useCaseImpl = object : CompletableUseCase<Boolean> {
            override val executionScheduler: Scheduler
                get() = testScheduler

            override val postExecutionScheduler: Scheduler
                get() = testScheduler

            override fun buildUseCase(params: Boolean): Completable {
                return Completable.complete()
            }
        }

        useCaseImpl.execute(true).test().assert()
    }

    @Test
    @DisplayName("MaybeUseCase")
    fun `default maybeusecase`() {
        val useCaseImpl = object : MaybeUseCase<String, Boolean> {
            override val executionScheduler: Scheduler
                get() = testScheduler

            override val postExecutionScheduler: Scheduler
                get() = testScheduler

            override fun buildUseCase(params: Boolean): Maybe<String> {
                return Maybe.just(params.toString())
            }
        }

        useCaseImpl.execute(true).test().assert(true.toString())
    }

    private fun UseCase<String, Boolean>.executeAndAssert(param: Boolean, expected: String) {
        val value = execute(param)

        testScheduler.triggerActions()
        assertThat(value).isEqualTo(expected)
    }

    private fun <T> TestObserver<T>.assert(value: T? = null) {
        testScheduler.triggerActions()
        assertComplete()
        assertNoErrors()
        if (value != null) assertValue(value)
    }
}
