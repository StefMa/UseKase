package guru.stefma.cleancomponents.usecase.rx

import io.reactivex.Flowable

/**
 * A extension over the generic [RxUseCase] which wrap a [result][R] into a [Flowable] instance.
 *
 * It will use the provided [executionScheduler] and [postExecutionScheduler] as
 * [Flowable.subscribeOn] and [Flowable.observeOn].
 *
 * @param P the params you want to put in to the UseCase
 * @param R the result value which will be emitted by this UseCase
 */
interface FlowableUseCase<R, P> : RxUseCase<Flowable<R>, P> {

    override fun execute(params: P): Flowable<R> = buildUseCase(params)
            .subscribeOn(executionScheduler)
            .observeOn(postExecutionScheduler)
}