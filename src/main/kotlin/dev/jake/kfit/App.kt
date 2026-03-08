package dev.jake.kfit

import dev.jake.kfit.di.DI
import io.ktor.http.*
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

    val userRepository = DI.appComponent.userRepository()
    val workoutRepository = DI.appComponent.workoutRepository()

    Flyway.configure()
        .dataSource(DI.appComponent.dataSource())
        .locations("classpath:db/migration")
        .load()
        .migrate()

    routing {
        get("/") {call.respondText("Welcome to kFit")}

        get("/health") {
            call.respond(DI.appComponent.metricsService().healthCheck())
        }

        get("/users") { call.respond(userRepository.findAll()) }

        get("/users/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)

            val user = userRepository.findById(id)
                ?: return@get call.respond(HttpStatusCode.NotFound)

            call.respond(user)
        }

        get("/workouts/exercises") {
            call.respond(workoutRepository.findAllExercises())
        }

    }
}