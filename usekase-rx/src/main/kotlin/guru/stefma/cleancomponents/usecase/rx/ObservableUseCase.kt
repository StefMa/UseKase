package guru.stefma.cleancomponents.usecase.rx

import io.reactivex.Observable

/**
 * A extension over the generic [RxUseCase] which wrap a [result][R] into a [Observable] instance.
 *
 * It will use the provided [executionScheduler] and [postExecutionScheduler] as
 * [Observable.subscribeOn] and [Observable.observeOn].
 *
 * @param P the params you want to put in to the UseCase
 * @param R the result value which will be emitted by this UseCase
 */
interface ObservableUseCase<R, P> : RxUseCase<Observable<R>, P> {

    override fun execute(params: P): Observable<R> = buildUseCase(params)
            .subscribeOn(executionScheduler)
            .observeOn(postExecutionScheduler)
}