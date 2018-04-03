package guru.stefma.cleancomponents.usecase

import io.reactivex.Scheduler

/**
 * A RxJava implementation of a [UseCase] which provides two [schedulers][Scheduler] which can be overriden to define
 * the work on the execution phase and the postExecution phase.
 *
 * Normally you don't want to use this interface directly but one of the subclasses of it.
 *
 * @see ObservableUseCase
 * @see SingleUseCase
 * @see MaybeUseCase
 * @see CompletableUseCase
 */
interface RxUseCase<out R, in P> : UseCase<R, P> {

    val executionScheduler: Scheduler

    val postExecutionScheduler: Scheduler
}