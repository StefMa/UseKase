package guru.stefma.cleancomponents.sample.usecase

import guru.stefma.cleancomponents.annotation.UseCase
import guru.stefma.cleancomponents.usekase.CompletableUseCase
import guru.stefma.cleancomponents.usekase.MaybeUseCase
import guru.stefma.cleancomponents.usekase.ObservableUseCase
import guru.stefma.cleancomponents.usekase.RxUseCase
import guru.stefma.cleancomponents.usekase.SingleUseCase
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single

@UseCase
class TestSingleUseCase(
        override val executionScheduler: Scheduler,
        override val postExecutionScheduler: Scheduler
) : SingleUseCase<String, Boolean> {

    override fun buildUseCase(params: Boolean): Single<String> {
        return Single.just("AString")
    }
}

@UseCase
class AwesomeSingleMoreUseCase(
        override val executionScheduler: Scheduler,
        override val postExecutionScheduler: Scheduler
) : SingleUseCase<Boolean, Boolean> {

    override fun buildUseCase(params: Boolean): Single<Boolean> {
        return Single.just(true)
    }
}

@UseCase
class SoProudOfMeSingleUseCase(
        override val executionScheduler: Scheduler,
        override val postExecutionScheduler: Scheduler
) : SingleUseCase<Array<String>, Boolean> {

    override fun buildUseCase(params: Boolean): Single<Array<String>> {
        return Single.just(emptyArray())
    }
}

@UseCase
class TestRxUseCase(
        override val executionScheduler: Scheduler,
        override val postExecutionScheduler: Scheduler
) : RxUseCase<Array<String>, Boolean> {

    override fun buildUseCase(params: Boolean): Array<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

@UseCase
class TestObservableUseCase(
        override val executionScheduler: Scheduler,
        override val postExecutionScheduler: Scheduler
) : ObservableUseCase<Array<String>, Boolean> {

    override fun buildUseCase(params: Boolean): Observable<Array<String>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

@UseCase
class TestMaybeUseCase(
        override val executionScheduler: Scheduler,
        override val postExecutionScheduler: Scheduler
) : MaybeUseCase<List<List<Boolean>>, Boolean> {

    override fun buildUseCase(params: Boolean): Maybe<List<List<Boolean>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

@UseCase
class TestCompletableUseCase(
        override val executionScheduler: Scheduler,
        override val postExecutionScheduler: Scheduler
) : CompletableUseCase<List<List<Boolean>>> {

    override fun buildUseCase(params: List<List<Boolean>>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

data class Params(val aBool: Boolean)

@UseCase
class TestGenericUseCase: guru.stefma.cleancomponents.usekase.UseCase<Array<Boolean>, Params> {

    override fun buildUseCase(params: Params): Array<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}