plugins {
    kotlin("jvm") version "1.2.31"
    kotlin("kapt") version "1.2.31"
}

repositories {
    jcenter()
}

dependencies {
    compile(project(":usekase"))
    kapt(project(":usekase-processor"))
    compile("org.jetbrains.kotlin:kotlin-stdlib-jre7:1.2.31")
    compile("io.reactivex.rxjava2:rxjava:2.1.12")

    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.1.0")
    testCompile("org.junit.jupiter:junit-jupiter-api:5.1.0")
    testCompile("org.assertj:assertj-core:3.9.0")
    testCompile("com.nhaarman:mockito-kotlin:1.5.0")
}

// Add generated kapt source code to main sourceSet
java.sourceSets {
    getByName("main") {
        java.srcDir("$buildDir/generated/source/kaptKotlin")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}