

plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.serialization") version "2.1.20"
    kotlin("kapt") version "2.1.20"
    application
}
group = "dev.jake"

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val ktorVersion: String by project
val logbackVersion: String by project
val daggerVersion: String by project

dependencies {
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-call-logging:$ktorVersion")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    implementation("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion")
    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-client-core:${ktorVersion}")
    testImplementation("io.ktor:ktor-client-content-negotiation:${ktorVersion}")
    testImplementation("io.ktor:ktor-serialization-kotlinx-json:${ktorVersion}")
}

application {
    mainClass.set("dev.jake.kfit.AppKt")
}
kotlin {
    jvmToolchain(21)
}
