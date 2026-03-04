package dev.jake.kfit

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

    val dataSource = createDataSource()
    val dsl = createDSLContext(dataSource)

    val appComponent = DaggerAppComponent.create()
    val metricsService = appComponent.metricsService()

    Flyway.configure()
        .dataSource(dataSource)
        .locations("classpath:db/migration")
        .load()
        .migrate()

    routing {
        get("/") {call.respondText("Welcome to kFit")}
        get("/health") {
            call.respond(metricsService.healthCheck())
        }
//        get("/users") {
//            val users = dsl
//                .select(USERS.ID, USERS.NAME)
//        }

    }
}