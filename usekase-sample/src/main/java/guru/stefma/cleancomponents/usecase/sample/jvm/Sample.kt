package guru.stefma.cleancomponents.usecase.sample.jvm

import guru.stefma.cleancomponents.annotation.UseCase
import guru.stefma.cleancomponents.usecase.SingleUseCase
import guru.stefma.cleancomponents.usecase.sample.jvm.GetUserUseCase.Params
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.Random

enum class Gender {
    MALE, FEMALE
}

data class User(
        val id: String,
        val name: String,
        val gender: Gender,
        val authorizationKey: String
)

/**
 * A implementation of a [SingleUseCase] which returns a [User] for a given [userId][Params.userId].
 */
@UseCase
class GetUserUseCase(
        override val executionScheduler: Scheduler = Schedulers.io(),
        override val postExecutionScheduler: Scheduler = Schedulers.trampoline()
) : SingleUseCase<User, GetUserUseCase.Params> {

    override fun buildUseCase(params: GetUserUseCase.Params): Single<User> {
        return Single.just(User(params.userId, "Thorsten", Gender.MALE, Random().nextInt(1000).toString()))
    }

    data class Params(val userId: String)
}

/**
 * A [SingleUseCase] which use the [GetUser][GetUserUseCase] to get the points of that user.
 *
 * We have to use [GetUser][GetUserUseCase] here to get the [User.authorizationKey].
 */
@UseCase
class GetUsePointsForUserIdUseCase(
        private val getUser: GetUser,
        override val executionScheduler: Scheduler,
        override val postExecutionScheduler: Scheduler
) : SingleUseCase<Int, GetUsePointsForUserIdUseCase.Params> {

    override fun buildUseCase(params: GetUsePointsForUserIdUseCase.Params): Single<Int> {
        return getUser.buildUseCase(
                guru.stefma.cleancomponents.usecase.sample.jvm.GetUserUseCase.Params(params.userId))
                .flatMap {
                    Single.just(it.authorizationKey)
                            .map { 99 }
                }
    }

    data class Params(val userId: String)
}