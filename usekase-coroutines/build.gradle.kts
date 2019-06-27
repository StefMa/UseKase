plugins {
    kotlin("jvm") version "1.3.40"
    id("com.novoda.bintray-release") version "0.9.1"
}

repositories {
    jcenter()
}

val coroutinesVersion = "1.2.1"

dependencies {
    api(project(":usekase-annotation"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    implementation(kotlin("stdlib-jdk7"))

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
    testImplementation("org.assertj:assertj-core:3.12.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publish {
    userOrg = "stefma"
    groupId = "guru.stefma.cleancomponents"
    artifactId = "usekase-coroutines"
    uploadName = "UseKase"
    publishVersion = "1.0.0"
    desc = "Provides a coroutine UseCase implementation for the Clean Architecture"
    website = "https://github.com/StefMa/UseKase"
    setLicences("MIT")
}
