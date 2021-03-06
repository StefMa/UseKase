plugins {
    kotlin("jvm") version "1.3.21"
    id("com.novoda.bintray-release") version "0.9.1"
}

repositories {
    jcenter()
}

dependencies {
    api(project(":usekase-annotation"))
    implementation(kotlin("stdlib-jdk7"))

    testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
    testImplementation("org.assertj:assertj-core:3.12.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publish {
    userOrg = "stefma"
    groupId = "guru.stefma.cleancomponents"
    artifactId = "usekase"
    uploadName = "UseKase"
    publishVersion = "1.0.0"
    desc = "Provides default UseCase implementations for the Clean Architecture"
    website = "https://github.com/StefMa/UseKase"
    setLicences("MIT")
}
