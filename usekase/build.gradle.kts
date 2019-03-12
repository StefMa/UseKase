plugins {
    kotlin("jvm") version "1.3.21"
    id("com.novoda.bintray-release") version "0.8.1"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))
    implementation("io.reactivex.rxjava2:rxjava:2.2.7")

    testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publish {
    userOrg = "stefma"
    groupId = "guru.stefma.cleancomponents"
    artifactId = "usekase"
    uploadName = "UseKase"
    publishVersion = "0.0.4"
    desc = "Provides default UseCase implementations for the Clean Architecture"
    website = "https://github.com/StefMa/UseKase"
    setLicences("MIT")
}
