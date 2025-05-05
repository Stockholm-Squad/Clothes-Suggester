plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.serialization") version "1.9.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"
val koinVersion = "3.5.3"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    koinDependencies()

    testingDependencies()
    coroutinesDependencies()
    ktorDependencies()
}

fun koinDependencies() {
    dependencies {
        implementation("io.insert-koin:koin-core:$koinVersion")
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

fun testingDependencies() {
    dependencies {
        testImplementation("io.mockk:mockk:1.13.9")
        testImplementation("com.google.truth:truth:1.4.2")
        testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    }
}

fun ktorDependencies() {
    dependencies {
        implementation("ch.qos.logback:logback-classic:1.4.11")
        implementation("io.ktor:ktor-client-core:2.3.5")
        implementation("io.ktor:ktor-client-cio:2.3.5")
        implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
        implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")
    }
}

fun coroutinesDependencies() {
    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    }
}