package guru.stefma.cleancomponents.sample.usecase

import guru.stefma.cleancomponents.usecase.annotation.UseCase
import guru.stefma.cleancomponents.usecase.rx.MaybeUseCase
import guru.stefma.cleancomponents.usecase.rx.ObservableUseCase
import guru.stefma.cleancomponents.usecase.rx.RxUseCase
import guru.stefma.cleancomponents.usecase.rx.SingleUseCase
import guru.stefma.cleancomponents.usecase.rx.CompletableUseCase
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
class CustomUseCase(
        override val executionScheduler: Scheduler,
        override val postExecutionScheduler: Scheduler
) : CompletableUseCase<CustomUseCase.Uhu<String, Int, Pair<String, Int>>> {

    override fun buildUseCase(params: Uhu<String, Int, Pair<String, Int>>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class Uhu<T, P, K> {

    }
}

@UseCase
class TestSingleMPairUseCase(
        override val executionScheduler: Scheduler,
        override val postExecutionScheduler: Scheduler
) : SingleUseCase<Pair<String, Int>, Boolean> {

    override fun buildUseCase(params: Boolean): Single<Pair<String, Int>> {
        return Single.just(Pair("", 2))
    }
}

@UseCase
class TestSingleMapUseCase(
        override val executionScheduler: Scheduler,
        override val postExecutionScheduler: Scheduler
) : SingleUseCase<Map<String, Int>, Boolean> {

    override fun buildUseCase(params: Boolean): Single<Map<String, Int>> {
        return Single.just(emptyMap())
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
        return emptyArray()
    }
}

@UseCase
class TestObservableUseCase(
        override val executionScheduler: Scheduler,
        override val postExecutionScheduler: Scheduler
) : ObservableUseCase<Array<String>, Boolean> {

    override fun buildUseCase(params: Boolean): Observable<Array<String>> {
        return Observable.just(emptyArray())
    }
}

@UseCase
class TestMaybeUseCase(
        override val executionScheduler: Scheduler,
        override val postExecutionScheduler: Scheduler
) : MaybeUseCase<List<List<Boolean>>, Boolean> {

    override fun buildUseCase(params: Boolean): Maybe<List<List<Boolean>>> {
        return Maybe.error(Throwable())
    }
}

@UseCase
class TestCompletableUseCase(
        override val executionScheduler: Scheduler,
        override val postExecutionScheduler: Scheduler
) : CompletableUseCase<List<List<Boolean>>> {

    override fun buildUseCase(params: List<List<Boolean>>): Completable {
        return Completable.complete()
    }
}

@UseCase
class TestCompletableMapUseCase(
        override val executionScheduler: Scheduler,
        override val postExecutionScheduler: Scheduler
) : CompletableUseCase<Map<String, Int>> {

    override fun buildUseCase(params: Map<String, Int>): Completable {
        return Completable.complete()
    }
}

data class Params(val aBool: Boolean)

@UseCase
class TestGenericUseCase : guru.stefma.cleancomponents.usecase.UseCase<Array<Boolean>, Params> {

    override fun buildUseCase(params: Params): Array<Boolean> {
        return arrayOf(params.aBool)
    }
}