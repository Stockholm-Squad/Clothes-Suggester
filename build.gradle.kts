plugins {
    kotlin("jvm") version "2.1.20"
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
}

fun koinDependencies() {
    dependencies {
        implementation("io.insert-koin:koin-core:$koinVersion")
        testImplementation("io.mockk:mockk:1.13.9")
        testImplementation("com.google.truth:truth:1.4.2")
        testImplementation("io.kotest:kotest-assertions-core:5.8.0")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
        implementation("ch.qos.logback:logback-classic:1.4.11")
        implementation("io.ktor:ktor-client-core:2.3.5")
        implementation("io.ktor:ktor-client-cio:2.3.5")
        implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
        implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}