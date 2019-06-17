plugins {
    kotlin("jvm") version "1.3.21"
    id("com.novoda.bintray-release") version "0.8.1"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))
}

publish {
    userOrg = "stefma"
    groupId = "guru.stefma.cleancomponents"
    artifactId = "usekase-annotation"
    uploadName = "UseKase-Annotation"
    publishVersion = "1.0.0"
    desc = "Provides a UseCase annotation for the Clean Architecture"
    website = "https://github.com/StefMa/UseKase"
    setLicences("MIT")
}
