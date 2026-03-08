package dev.jake.kfit.models

import kotlinx.serialization.Serializable


@Serializable
data class Exercise(
    val id: Int,
    val name: String
)

@Serializable
data class LiftHistoryDTO(
    val exerciseName: String,
    val reps: Int,
    val weight: Double
)


