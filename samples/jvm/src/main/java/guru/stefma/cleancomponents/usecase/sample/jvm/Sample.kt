package guru.stefma.cleancomponents.usecase.sample.jvm

import guru.stefma.cleancomponents.usecase.annotation.UseCase
import guru.stefma.cleancomponents.usecase.coroutines.CoroutineUseCase
import guru.stefma.cleancomponents.usecase.rx.SingleUseCase
import guru.stefma.cleancomponents.usecase.sample.jvm.Gender.MALE
import guru.stefma.cleancomponents.usecase.sample.jvm.GetUserUseCase.Params
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import java.util.Random
import kotlin.coroutines.CoroutineContext

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
) : SingleUseCase<User, Params> {

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
            GetUserUseCase.Params(params.userId)
        ).flatMap {
            Single.just(it.authorizationKey)
                .map { 99 }
        }
    }

    data class Params(val userId: String)
}

@UseCase
class GetUserCoroutineUseCase(
    override val callbackDispatcher: CoroutineContext = Dispatchers.Default
) : CoroutineUseCase<User, GetUserCoroutineUseCase.Params> {

    override suspend fun buildUseCase(params: Params): Result<User> {
        return Result.success(User(params.userId, "Thorsten", MALE, Random().nextInt(1000).toString()))
    }

    data class Params(val userId: String)
}

@UseCase
class GetUsePointsForUserIdCoroutineUseCase(
    private val getUser: GetUserCoroutine,
    override val callbackDispatcher: CoroutineContext = Dispatchers.Default
) : CoroutineUseCase<Int, GetUsePointsForUserIdCoroutineUseCase.Params> {

    override suspend fun buildUseCase(params: Params): Result<Int> {
        return getUser.buildUseCase(GetUserCoroutineUseCase.Params(params.userId))
            .map { it.authorizationKey }
            .map { 99 }
    }

    data class Params(val userId: String)
}