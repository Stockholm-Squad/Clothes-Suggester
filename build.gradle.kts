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
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}