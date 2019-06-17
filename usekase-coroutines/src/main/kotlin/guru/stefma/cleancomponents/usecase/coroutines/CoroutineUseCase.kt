package guru.stefma.cleancomponents.usecase.coroutines

/**
 * A UseCase is a "actor" or "transformer" in the clean architecture.
 *
 * Use this interface as a default implementation of an interface which have [params][P] and a [result][R].
 *
 * Simply call [execute] with the given [params][P] to get the [result][R].
 *
 * @param P the params you want to put in to the UseCase
 * @param R the result value which will be emitted by this UseCase
 */
interface CoroutineUseCase<R, P> {

    suspend fun execute(params: P): R

    /**
     * For UseCases which don't need any parameters:
     * ```
     * MyUseCase : UseCase<Result, UseCase.None> {
     *
     *     override fun execute(params: None): Result { /* ... */ }
     *
     *     /* ... */
     * }
     *
     * MyUseCase().execute(UseCase.None)
     * ```
     */
    object None
}
