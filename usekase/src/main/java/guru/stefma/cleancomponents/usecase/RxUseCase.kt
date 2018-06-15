package guru.stefma.cleancomponents.usecase

import io.reactivex.Scheduler

/**
 * A RxJava implementation of a [UseCase] which provides two [schedulers][Scheduler] which can be overridden to define
 * the "thread" in the `execution`- and the `postExecution`-phase.
 *
 * Normally you don't want to use this interface directly but one of the subclasses of it.
 *
 * @param P the params you want to put in to the UseCase
 * @param R the result value which will be emitted by this UseCase
 *
 * @see ObservableUseCase
 * @see SingleUseCase
 * @see MaybeUseCase
 * @see CompletableUseCase
 */
interface RxUseCase<R, P> : UseCase<R, P> {

    val executionScheduler: Scheduler

    val postExecutionScheduler: Scheduler
}