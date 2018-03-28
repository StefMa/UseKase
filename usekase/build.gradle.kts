plugins {
    kotlin("jvm") version "1.2.31"
    id("com.novoda.bintray-release") version "0.8.1"
}

repositories {
    jcenter()
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jre7:1.2.31")
    compileOnly("io.reactivex.rxjava2:rxjava:2.1.12")
}

publish {
    userOrg = "stefma"
    groupId = "guru.stefma.cleancomponents"
    artifactId = "usekase"
    uploadName = "UseKase"
    publishVersion = "0.0.1"
    desc = "Provides default UseCase implementations for the Clean Architecture"
    website = "https://github.com/StefMa/UseKase"
}
