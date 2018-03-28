package guru.stefma.cleancomponents.annotation

/**
 * Use this annotation over an [UseCase][guru.stefma.cleancomponents.usekase.UseCase]
 * (or any other subclass of it) to automatically generate TypeAliases for it.
 *
 * The class should be follow the name convention `[Name]UseCase`.
 *
 * E.g. **`DoSomethingUseCase`**.
 * This will generate a TypeAlias with the name **`DoSomething`**.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class UseCase