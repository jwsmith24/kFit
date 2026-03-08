package dev.jake.kfit.routes

import dev.jake.kfit.repos.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes(userRepository: UserRepository) {

    route("/users") {
        get { call.respond(userRepository.findAll()) }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)

            val user = userRepository.findById(id)
                ?: return@get call.respond(HttpStatusCode.NotFound)

            call.respond(user)
        }

    }

}