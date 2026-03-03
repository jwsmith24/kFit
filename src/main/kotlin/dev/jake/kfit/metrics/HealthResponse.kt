package dev.jake.kfit.metrics

import kotlinx.serialization.Serializable

@Serializable
data class HealthResponse(
    val status: String,
    val time: String,
    val version: String
)
