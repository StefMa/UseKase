package guru.stefma.cleancomponents.usecase.rx

import io.reactivex.Single

/**
 * A extension over the generic [RxUseCase] which wrap a [result][R] into a [Single] instance.
 *
 * It will use the provided [executionScheduler] and [postExecutionScheduler] as
 * [Single.subscribeOn] and [Single.observeOn].
 *
 * @param P the params you want to put in to the UseCase
 * @param R the result value which will be emitted by this UseCase
 */
interface SingleUseCase<R, P> : RxUseCase<Single<R>, P> {

    override fun execute(params: P): Single<R> = buildUseCase(params)
            .subscribeOn(executionScheduler)
            .observeOn(postExecutionScheduler)
}