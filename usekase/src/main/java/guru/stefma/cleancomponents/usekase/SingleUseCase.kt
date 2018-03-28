package guru.stefma.cleancomponents.usekase

import io.reactivex.Single

/**
 * A extension over the generic [RxUseCase] which wrap a [result][R] into a [Single] instance.
 *
 * It will use the provided [executionScheduler] and [postExecutionScheduler] as
 * [Single.subscribeOn] and [Single.observeOn].
 */
interface SingleUseCase<R, in P> : RxUseCase<Single<R>, P> {

    override fun execute(params: P): Single<R> = buildUseCase(params)
            .subscribeOn(executionScheduler)
            .observeOn(postExecutionScheduler)
}