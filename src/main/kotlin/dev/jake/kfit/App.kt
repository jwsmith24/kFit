package dev.jake.kfit

import dev.jake.generated.tables.Users
import dev.jake.generated.tables.Users.USERS
import dev.jake.kfit.di.DaggerAppComponent
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.flywaydb.core.Flyway

fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }


    val appComponent = DaggerAppComponent.create()
    val dsl = appComponent.dslContext()

    Flyway.configure()
        .dataSource(appComponent.dataSource())
        .locations("classpath:db/migration")
        .load()
        .migrate()

    routing {
        get("/") {call.respondText("Welcome to kFit")}
        get("/health") {
            call.respond(appComponent.metricsService().healthCheck())
        }
        get("/users") {
            val users = dsl
                .select(USERS.ID, USERS.NAME)
                .from(USERS)
                // can pass the transform function directly into fetch as lambda
                .fetch{ "${it[USERS.ID]}: ${it[USERS.NAME]}"}

            call.respond(users)
        }

    }
}