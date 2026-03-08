package dev.jake.kfit.routes

import dev.jake.kfit.repos.WorkoutRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.workoutRoutes(workoutRepository: WorkoutRepository) {

    route("/workouts") {

        get("/exercises") {
            call.respond(workoutRepository.findAllExercises())
        }

        get("/history/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)

            val history = workoutRepository.findLiftHistoryByUserId(id)

            call.respond(history)
        }
    }


}