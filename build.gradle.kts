buildscript {
    dependencies {
        classpath("org.flywaydb:flyway-core:10.20.1")
        classpath("org.flywaydb:flyway-database-postgresql:10.20.1")
        classpath("org.postgresql:postgresql:42.7.3")
    }
}

plugins {
    kotlin("jvm") version "2.1.20"
    kotlin("plugin.serialization") version "2.1.20"
    kotlin("kapt") version "2.1.20"
    id("nu.studer.jooq") version "9.0"
    application
}
group = "dev.jake"

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.register("flywayMigrateForCodegen") {
    doLast {
        val flyway = org.flywaydb.core.Flyway.configure()
            .dataSource("jdbc:postgresql://localhost:5435/kfit_db", "kfit", "kfit")
            .locations("filesystem:src/main/resources/db/migration")
            .load()
        flyway.migrate()
    }
}

afterEvaluate {
    tasks.named("generateJooq").configure {
        dependsOn("flywayMigrateForCodegen")
    }

    tasks.named("compileKotlin").configure {
        dependsOn("generateJooq")
    }
}



val ktorVersion: String by project
val logbackVersion: String by project
val daggerVersion: String by project
val jooqVersion: String by project
val postgresVersion: String by project
val hikariVersion: String by project
val flywayVersion: String by project

dependencies {
    // ktor
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-call-logging:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    // dagger
    implementation("com.google.dagger:dagger:$daggerVersion")
    kapt("com.google.dagger:dagger-compiler:$daggerVersion")

    // database
    implementation("org.postgresql:postgresql:$postgresVersion")
    implementation("com.zaxxer:HikariCP:$hikariVersion")

    // jOOQ
    implementation("org.jooq:jooq:$jooqVersion")
    jooqGenerator("org.postgresql:postgresql:$postgresVersion")

    // flyway
    implementation("org.flywaydb:flyway-core:$flywayVersion")
    implementation("org.flywaydb:flyway-database-postgresql:$flywayVersion")


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


jooq {
    version.set(jooqVersion)
    configurations {
        create("main") {
            jooqConfiguration.apply {
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://localhost:5435/kfit_db"
                    user = "kfit"
                    password = "kfit"
                }
                generator.apply {
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "public"
                    }
                    target.apply {
                        packageName = "dev.jake.kfit.generated"
                        directory = "src/main/generated"
                    }
                }
            }
        }
    }
}

    // add generated jooq code to classpath
sourceSets.main {
    java.srcDirs("src/main/generated")
}