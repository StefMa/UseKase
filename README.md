[![CircleCI](https://img.shields.io/circleci/project/github/StefMa/UseKase.svg)](https://circleci.com/gh/StefMa/UseKase)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Download](https://api.bintray.com/packages/stefma/maven/UseKase/images/download.svg)](https://bintray.com/stefma/maven/UseKase/_latestVersion)

# UseKase
Provides default UseCase implementations for the Clean Architecture

## Description
UseKase is a **Kotlin** library which provides a set of default implementations of UseCases.
Most of the UseCases depend on heavily on RxJava2.

I found my self copying [these implementations](https://github.com/StefMa/UseKase/tree/master/usekase/src/main/java/guru/stefma/cleancomponents/usecase) in new projects again and again. Even if these contain only some lines of code it was just annoying. So I decided to create this library which provides these for me.

Because all Kotlin classes are `final` by default I created for each UseCase a `typealias` to the correct `interface`. This make the UseCase in Presenters testable. Because it was as annoying as copying and pasting the UseCase implementations I created an annotation processor which creates the `typealias` at runtime for me. You can find the code for that in the [usekase-processor](https://github.com/StefMa/UseKase/tree/master/usekase-processor) module.

## How to use it

### Get the dependencies
UseKase is available via jcenter:

```kotlin
repositories {
  jcenter()
}

dependencies {
  implementation("guru.stefma.cleancomponents:usekase:$useKaseVersion")

  // Alternatively (but highly recommended): usekase-processor
  kapt("guru.stefma.cleancomponents:usekase-processor:$useKaseVersion")
}
```

### Configurate the `sourceSet`
First you have to add the **generated source dir** to your `sourceSet`.
You can do this by adding the following into your `build.gradle` file:
```groovy
// Add the generated source as sourceSet
android.applicationVariants.all { variant ->
    variant.addJavaSourceFoldersToModel()
    def kotlinGenerated = file("$buildDir/generated/source/kaptKotlin/${variant.name}")
    variant.addJavaSourceFoldersToModel(kotlinGenerated)
}
```
For pure Kotlin projects the syntax is a little bit different. 
See [this issue](https://github.com/StefMa/UseKase/issues/1#issuecomment-378848103) for more.

### Create a `UseCase`
You can just implement one of the provided default UseCase implementations
and add the `@UseCase` annotation to it:
```kotlin
@UseCase
GetUserUseCase(
  override val executionScheduler: Scheduler = Schedulers.io(),
  override val postExecutionScheduler: Scheduler = AndroidSchedulers.mainThread(),
  private val api: BackendApi
) : SingleUseCase<User, String> {

  override fun buildUseCase(params: String): Single<User> {
    return api.getUserByUserId(params)
  }

}
```

If the `UseKase-Processor` is applied it will generated the following `typealias`:
```kotlin
typealias GetUser = SingleUseCase<User, String>
```

All available `UseCase` implementations are:
* `UseCase<R, P>`
* `RxUseCase<R, P>`
* `SingleUseCase<R, P>`
* `FlowableUseCase<R, P>`
* `ObservableUseCase<R, P>`
* `MaybeUseCase<R, P>`
* `CompletableUseCase<P>`

### Kotlin coroutine version
The `usekase-coroutines` module offers a base use case class `CoroutineUseCase` that you can extend:
```kotlin
@UseCase
class GetUserCoroutineUseCase(
    override val callbackDispatcher: CoroutineContext = Dispatchers.IO
) : CoroutineUseCase<User, GetUserCoroutineUseCase.Params>() {

    override suspend fun execute(params: Params): Result<User> {
        return Result.success(User(params.userId, "Thorsten", MALE, Random().nextInt(1000).toString()))
    }

    data class Params(val userId: String)
}
```

You have to execute it from a `CoroutineScope` like this:
```kotlin
GlobalScope.launch {
  getUserCoroutine(
    onFailure = { },
    onError = { }
  )
}
```

However, the module currently uses the experimental `Result` class as a return type. You need to enable this first
by putting this in your `build.gradle.kts`:
```groovy
tasks.withType(KotlinCompile::class.java).all {
    kotlinOptions.apply {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xallow-result-return-type")
    }
}
```