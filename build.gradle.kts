plugins {
    kotlin("jvm") version "2.3.0"
    kotlin("plugin.serialization") version "1.9.22"
    application
}

group = "dev.jake"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val ktorVersion: String by project
val logbackVersion: String by project

dependencies {
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-call-logging:$ktorVersion")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    implementation("io.ktor:ktor-server-content-negotiation:${ktorVersion}")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${ktorVersion}")

    testImplementation("io.ktor:ktor-server-test-host:${ktorVersion}")
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("dev.jake.kfit.AppKt")
}
kotlin {
    jvmToolchain(23)
}

tasks.test {
    useJUnitPlatform()
}