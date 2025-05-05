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