package guru.stefma.cleancomponents.processor.usecase

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeAliasSpec
import guru.stefma.cleancomponents.annotation.UseCase
import me.eugeniomarletti.kotlin.metadata.KotlinClassMetadata
import me.eugeniomarletti.kotlin.metadata.KotlinMetadataUtils
import me.eugeniomarletti.kotlin.metadata.extractFullName
import me.eugeniomarletti.kotlin.metadata.kotlinMetadata
import me.eugeniomarletti.kotlin.processing.KotlinAbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class UseCaseProcessor : KotlinAbstractProcessor(), KotlinMetadataUtils {

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        roundEnv.getElementsAnnotatedWith(UseCase::class.java).forEach {
            if (it.kind != ElementKind.CLASS) {
                return false
            }

            val className = it.simpleName.toString()
            val classPackage = elementUtils.getPackageOf(it).toString()
            val fullName = it.fullName()

            val generatedClass = GeneratedClass(messager, className, classPackage, fullName)
            createTypeAlias(generatedClass)
        }

        return true
    }

    private fun createTypeAlias(generatedClass: GeneratedClass) = generatedDir?.also {
        val file = FileSpec.builder(generatedClass.classPackage, generatedClass.fileName)
                .addTypeAlias(
                        TypeAliasSpec.builder(generatedClass.className, generatedClass.typeNameFromGenerics).build()
                )
                .build()

        it.mkdirs()
        file.writeTo(it)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return setOf(UseCase::class.java.name).toMutableSet()
    }
}

// Workaround section.
// See https://github.com/square/kotlinpoet/issues/236

/**
 * Returns the "fullname" of this [Element] first superclass (see [ProtoBuf.Class.supertypeList[0]]).
 *
 * The name will be (for example):
 * ```
 *     guru.stefma.cleancomponents.usecase.ObservableUseCase<kotlin.Array<kotlin.String>, kotlin.Boolean>
 * ```
 */
private fun Element.fullName(): String {
    val metadata = kotlinMetadata as KotlinClassMetadata
    val proto = metadata.data.classProto
    val name = proto.supertypeList[0].extractFullName(metadata.data)
    return name.replace("`", "").replace("`", "")
}