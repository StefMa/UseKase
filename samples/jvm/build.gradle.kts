plugins {
    kotlin("jvm") version "1.3.21"
    kotlin("kapt") version "1.3.21"
}

repositories {
    jcenter()
}

dependencies {
    implementation(project(":usekase-rx"))
    implementation(project(":usekase-coroutines"))
    kapt(project(":usekase-processor"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.21")
    // This is for usekase-rx
    implementation("io.reactivex.rxjava2:rxjava:2.2.7")
    // This is for usekase-coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.4.0")
    testImplementation("org.assertj:assertj-core:3.12.1")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
}

// Add generated kapt source code to main sourceSet
sourceSets {
    getByName("main") {
        java.srcDir("$buildDir/generated/source/kaptKotlin")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}