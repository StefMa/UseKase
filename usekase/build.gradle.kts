plugins {
    kotlin("jvm") version "1.2.60"
    id("com.novoda.bintray-release") version "0.8.1"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))
    implementation("io.reactivex.rxjava2:rxjava:2.2.0")

    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testImplementation("org.assertj:assertj-core:3.10.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publish {
    userOrg = "stefma"
    groupId = "guru.stefma.cleancomponents"
    artifactId = "usekase"
    uploadName = "UseKase"
    publishVersion = "0.0.3"
    desc = "Provides default UseCase implementations for the Clean Architecture"
    website = "https://github.com/StefMa/UseKase"
    setLicences("MIT")
}
