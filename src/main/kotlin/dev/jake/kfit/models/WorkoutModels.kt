package dev.jake.kfit.models

import kotlinx.serialization.Serializable


@Serializable
data class Exercise (
    val id: Int,
    val name: String
)


