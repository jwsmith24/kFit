package dev.jake.kfit

import dev.jake.kfit.di.DaggerAppComponent
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
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
    val userRepository = appComponent.userRepository()

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

        get("/users") { call.respond(userRepository.findAll()) }

        get("/users/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)

            val user = userRepository.findById(id)
                ?: return@get call.respond(HttpStatusCode.NotFound)

            call.respond(user)
        }

    }
}