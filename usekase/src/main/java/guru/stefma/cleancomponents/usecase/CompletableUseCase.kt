package guru.stefma.cleancomponents.usecase

import io.reactivex.Completable

/**
 * A extension over the generic [RxUseCase] which transform the result into a [Completable] instance.
 *
 * It will use the provided [executionScheduler] and [postExecutionScheduler] as
 * [Completable.subscribeOn] and [Completable.observeOn].
 */
interface CompletableUseCase<P> : RxUseCase<Completable, P> {

    override fun execute(params: P): Completable = buildUseCase(params)
            .subscribeOn(executionScheduler)
            .observeOn(postExecutionScheduler)
}
