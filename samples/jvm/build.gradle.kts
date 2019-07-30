import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.41"
    kotlin("kapt") version "1.3.41"
}

repositories {
    jcenter()
}

dependencies {
    implementation(project(":usekase-rx"))
    implementation(project(":usekase-coroutines"))
    kapt(project(":usekase-processor"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.41")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.4.0")
    testImplementation("org.assertj:assertj-core:3.12.2")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.2.2")
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

tasks.withType(KotlinCompile::class.java).configureEach {
    kotlinOptions.apply {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xallow-result-return-type")
    }
}