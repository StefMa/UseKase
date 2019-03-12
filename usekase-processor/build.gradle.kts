plugins {
    kotlin("jvm") version "1.3.21"
    kotlin("kapt") version "1.3.21"
    id("com.novoda.bintray-release") version "0.8.1"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))
    implementation("com.squareup:kotlinpoet:0.7.0")
    implementation("me.eugeniomarletti.kotlin.metadata:kotlin-metadata:1.4.0")
    implementation("com.google.auto.service:auto-service:1.0-rc4")
    kapt("com.google.auto.service:auto-service:1.0-rc4")
    compileOnly(project(":usekase-rx"))
}

publish {
    userOrg = "stefma"
    groupId = "guru.stefma.cleancomponents"
    artifactId = "usekase-processor"
    uploadName = "UseKase-Processor"
    publishVersion = "0.0.4"
    desc = "Provides a annotation process for the default UseCase implementations for the Clean Architecture"
    website = "https://github.com/StefMa/UseKase"
    setLicences("MIT")
}
