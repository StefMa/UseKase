package guru.stefma.cleancomponents.usecase

import io.reactivex.Maybe

/**
 * A extension over the generic [RxUseCase] which wrap a [result][R] into a [Maybe] instance.
 *
 * It will use the provided [executionScheduler] and [postExecutionScheduler] as
 * [Maybe.subscribeOn] and [Maybe.observeOn].
 *
 * @param P the params you want to put in to the UseCase
 * @param R the result value which will be emitted by this UseCase
 */
interface MaybeUseCase<R, P> : RxUseCase<Maybe<R>, P> {

    override fun execute(params: P): Maybe<R> = buildUseCase(params)
            .subscribeOn(executionScheduler)
            .observeOn(postExecutionScheduler)
}