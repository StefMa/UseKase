package guru.stefma.cleancomponents.usekase

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
interface UseCase<out R, in P> {

    fun buildUseCase(params: P): R

    fun execute(params: P) = buildUseCase(params)
}