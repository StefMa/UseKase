package guru.stefma.cleancomponents.processor.usekase

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.TypeAliasSpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import guru.stefma.cleancomponents.annotation.UseCase
import guru.stefma.cleancomponents.usekase.CompletableUseCase
import guru.stefma.cleancomponents.usekase.MaybeUseCase
import guru.stefma.cleancomponents.usekase.ObservableUseCase
import guru.stefma.cleancomponents.usekase.RxUseCase
import guru.stefma.cleancomponents.usekase.SingleUseCase
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.lang.model.type.DeclaredType

private class ClassInfo(
        val classPackage: String,
        _className: String,
        val classType: ClassName,
        val argumentTypes: Array<TypeName>
) {

    val className = _className.replace("UseCase", "")
}

@AutoService(Processor::class)
class UseCaseProcessor : AbstractProcessor() {

    private val useCaseTypeName by lazy {
        listOf(
                RxUseCase::class.asTypeName(),
                SingleUseCase::class.asTypeName(),
                ObservableUseCase::class.asTypeName(),
                MaybeUseCase::class.asTypeName(),
                CompletableUseCase::class.asTypeName(),
                guru.stefma.cleancomponents.usekase.UseCase::class.asTypeName()
        )
    }

    private val useCaseNames by lazy {
        listOf(
                RxUseCase::class.qualifiedName,
                SingleUseCase::class.qualifiedName,
                ObservableUseCase::class.qualifiedName,
                MaybeUseCase::class.qualifiedName,
                CompletableUseCase::class.qualifiedName,
                guru.stefma.cleancomponents.usekase.UseCase::class.qualifiedName
        )
    }

    @Synchronized
    override fun process(elements: MutableSet<out TypeElement>, environment: RoundEnvironment): Boolean {
        // processingEnv.messager.printMessage(WARNING, "Hello World")
        environment.getElementsAnnotatedWith(UseCase::class.java).forEach {
            if (it.kind != ElementKind.CLASS) {
                return false
            }

            val className = it.simpleName.toString()
            val classPackage = processingEnv.elementUtils.getPackageOf(it).toString()
            ((it as TypeElement).interfaces).forEach {
                val declaredType = it as DeclaredType

                useCaseNames.find { it == declaredType.asElement().toString() }?.also {
                    val classInfo = ClassInfo(
                            classPackage = classPackage,
                            _className = className,
                            classType = useCaseTypeName[useCaseNames.indexOf(it)],
                            argumentTypes = declaredType.typeArguments.map { it.asTypeName() }.toTypedArray()
                    )
                    createClass(classInfo)
                }

            }
        }

        return true
    }

    private fun createClass(classInfo: ClassInfo) {
        val extension = ParameterizedTypeName.get(classInfo.classType, *classInfo.argumentTypes)
        val file = FileSpec.builder(classInfo.classPackage, "${classInfo.className}TypeAlias")
                .addTypeAlias(TypeAliasSpec.builder(classInfo.className, extension).build())
                .build()

        val folder = File(processingEnv.options["kapt.kotlin.generated"]).apply { mkdirs() }
        file.writeTo(folder)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return setOf(UseCase::class.java.name).toMutableSet()
    }
}