package guru.stefma.cleancomponents.usecase.annotation

/**
 * Use this annotation over an [UseCase][guru.stefma.cleancomponents.usekase.UseCase]
 * (or any other subclass of it) to automatically generate TypeAliases for it.
 *
 * The class should be follow the name convention `[Name]UseCase`.
 *
 * E.g. **`DoSomethingUseCase`**.
 * This will generate a TypeAlias with the name **`DoSomething`**.
 *
 * If you want to override the default naming you can set the [UseCase.name] which will
 * then be used as `TypeAlias` name.
 *
 * Additionally you can set a [UseCase.prefix] and/or a [UseCase.suffix].
 *
 * ### The general rule is the following:
 * * Is no parameter is set: it remove the `UseCase` from the name
 * * Is name is set: it uses this name. name can't be combined.
 * * Is prefix is set: it uses this prefix in front of the classname
 * * Is suffix is set: it uses this suffix after the classname.
 *
 * prefix & suffix can be combined. But name can not.
 *
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class UseCase(
        val name: String = "",
        val prefix: String = "",
        val suffix: String = ""
)