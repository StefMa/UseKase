<!--[![CircleCI](https://img.shields.io/circleci/project/github/StefMa/UseKase.svg)](https://circleci.com/gh/StefMa/UseKase)-->
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
<!--[![Download](https://api.bintray.com/packages/stefma/maven/UseKase/images/download.svg) ](https://bintray.com/stefma/maven/UseKase/_latestVersion)-->

# UseKase
Provides default UseCase implementations for the Clean Architecture

<!--
## Description
// TODO
-->


## How to use it

### Get the dependencies
UseKase is available via jcenter:

```kotlin
repositories {
  jcenter()
}

dependencies {
  implementation("guru.stefma.cleancomponents:usekase:$useKaseVersion")
  // Needed for UseKase: Kotlin and RxJava2
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
  implementation("io.reactivex.rxjava2:rxjava:$rxJava2Version")

  // Alternatively (but highly recommended): usekase-processor
  implementation("guru.stefma.cleancomponents:usekase-processor:$useKaseVersion")
}
```

### Usage
Just implemented one of the provided default UseCase implementation and add the `@UseCase` annotation to it:
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
* `ObservableUseCase<R, P>`
* `MaybeUseCase<R, P>`
* `CompletableUseCase<P>`
