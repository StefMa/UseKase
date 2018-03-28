package guru.stefma.cleancomponents.usekase

import io.reactivex.Maybe

/**
 * A extension over the generic [RxUseCase] which wrap a [result][R] into a [Maybe] instance.
 *
 * It will use the provided [executionScheduler] and [postExecutionScheduler] as
 * [Maybe.subscribeOn] and [Maybe.observeOn].
 */
interface MaybeUseCase<R, in P> : RxUseCase<Maybe<R>, P> {

    override fun execute(params: P): Maybe<R> = buildUseCase(params)
            .subscribeOn(executionScheduler)
            .observeOn(postExecutionScheduler)
}