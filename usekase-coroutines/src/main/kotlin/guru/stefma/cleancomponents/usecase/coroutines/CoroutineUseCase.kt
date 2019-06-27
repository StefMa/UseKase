package guru.stefma.cleancomponents.usecase.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * A UseCase is an "actor" or "transformer" in the Clean Architecture.
 *
 * Use this base class to create a use case with input [parameters][Params] and an expected [result][Type].
 *
 * By default, once your overridden [execute] method returns, the callback lambdas passed to [invoke] will execute
 * on the [main thread][Dispatchers.Main]. This requires a runtime dependency of coroutines that adds a main
 * dispatcher. You can override [callbackDispatcher] to change this behavior.
 *
 * @param Type The type which you expect to be returned by the use case.
 * @param Params The parameters which the use case needs as an input.
 */
abstract class CoroutineUseCase<out Type, in Params> where Type : Any {

    open val callbackDispatcher: CoroutineContext = Dispatchers.Main

    /**
     * Runs the actual logic of the use case.
     */
    abstract suspend fun execute(params: Params): Result<Type>

    suspend operator fun invoke(params: Params, onSuccess: (Type) -> Unit, onFailure: (Throwable) -> Unit) {
        val result = execute(params)

        coroutineScope {
            launch(callbackDispatcher) {
                result.fold(
                    onFailure = onFailure,
                    onSuccess = onSuccess
                )
            }
        }
    }
}