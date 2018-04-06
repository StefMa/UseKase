package guru.stefma.cleancomponents.processor.usecase

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.TypeVariableName
import javax.annotation.processing.Messager
import javax.lang.model.element.Element

/**
 * A utility class which should be constructed with three information:
 * * _clazzName // The origin name of the class (E.g. GetUserUserCase)
 * * clazzPackage // The package of that class (E.g. guru.stefma.app.usecase)
 * * fullName // The full "name" of the class (E.g. guru.stefma.app.usecase.ObservableUseCase<kotlin.Array<kotlin.String>, kotlin.Boolean>)
 *
 * You can then access the following properties:
 *
 * [fileName] // This is [className]+TypeAlias (E.g. GetUserTypeAlias)
 * [className] // This is [_className]-UseCase (E.g. GetUser)
 * [classPackage] // This is the [classPackage] without any modification (E.g. guru.stefma.app.usecase)
 * [typeNameFromGenerics] // A [TypeName] which contains all generics in it. (E.g. guru.stefma.app.usecase.ObservableUseCase<kotlin.Array<kotlin.String>, kotlin.Boolean>)
 */
internal data class GeneratedClass(
        private val messager: Messager,
        private val _className: String,
        val classPackage: String,
        private val fullName: String
) {

    val fileName by lazy {
        "${className}TypeAlias"
    }

    val className by lazy {
        _className.replace("UseCase", "")
    }

    /**
     * This is the type of that UseCase (E.g. guru.stefma.app.usecase.ObservableUseCase)
     */
    private val type by lazy {
        fullName.type()
    }

    val typeNameFromGenerics by lazy {
        val genericType = TypeVariableName.invoke(fullName.generics())
        // Wrap the generics in our type (e.g. guru.stefma.app.usecase.ObservableUseCase)
        ParameterizedTypeName.get(ClassName.bestGuess(type), genericType)
    }
}

// Workaround section.
// See https://github.com/square/kotlinpoet/issues/236

/**
 * Return the "type" from the returned [Element.fullName].
 *
 * The type will be (for example):
 * ```
 *     guru.stefma.cleancomponents.usecase.ObservableUseCase
 * ```
 */
private fun String.type() = substring(0, indexOfFirst { it == '<' })

/**
 * Return the "generics" from the returned [Element.fullName]
 *
 * The list will be (for example):
 * ```
 *     <kotlin.Array<kotlin.String>>, <kotlin.Boolean>
 * ```
 */
private fun String.generics(): String {
    val first = indexOfFirst { it == '<' }
    val last = indexOfLast { it == '>' }
    return substring(first + 1, last)
}
