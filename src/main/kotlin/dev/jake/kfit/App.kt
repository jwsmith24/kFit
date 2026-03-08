package dev.jake.kfit

import dev.jake.kfit.di.DI
import dev.jake.kfit.routes.userRoutes
import dev.jake.kfit.routes.workoutRoutes
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
    install(ContentNegotiation) { json() }
    install(IgnoreTrailingSlash)


    Flyway.configure()
        .dataSource(DI.appComponent.dataSource())
        .locations("classpath:db/migration")
        .load()
        .migrate()

    routing {
        get("/") {call.respondText("Welcome to kFit")}
        get("/health") { call.respond(DI.appComponent.metricsService().healthCheck()) }

        userRoutes(DI.appComponent.userRepository())
        workoutRoutes(DI.appComponent.workoutRepository())
    }
}