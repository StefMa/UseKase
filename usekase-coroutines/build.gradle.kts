plugins {
    kotlin("jvm") version "1.3.21"
    id("com.novoda.bintray-release") version "0.8.1"
}

repositories {
    jcenter()
}

dependencies {
    api(project(":usekase-annotation"))
    implementation(kotlin("stdlib-jdk7"))

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1")
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
    uploadName = "UseKase-Coroutines"
    publishVersion = "1.0.0"
    desc = "Provides a corotuine UseCase implementation for the Clean Architecture"
    website = "https://github.com/StefMa/UseKase"
    setLicences("MIT")
}
