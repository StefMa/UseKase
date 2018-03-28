package guru.stefma.cleancomponents.usekase

import io.reactivex.Observable

/**
 * A extension over the generic [RxUseCase] which wrap a [result][R] into a [Observable] instance.
 *
 * It will use the provided [executionScheduler] and [postExecutionScheduler] as
 * [Observable.subscribeOn] and [Observable.observeOn].
 */
interface ObservableUseCase<R, in P> : RxUseCase<Observable<R>, P> {

    override fun execute(params: P): Observable<R> = buildUseCase(params)
            .subscribeOn(executionScheduler)
            .observeOn(postExecutionScheduler)
}