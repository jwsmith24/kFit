package dev.jake.kfit.routes

import dev.jake.kfit.repos.WorkoutRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlin.text.toIntOrNull

fun Route.workoutRoutes(workoutRepository: WorkoutRepository) {

    route("/workouts" ) {

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