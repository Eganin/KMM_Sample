package models

import kotlinx.serialization.Serializable

@Serializable
data class CreateGameInfo(
    val title: String,
    val description: String,
    val version: String,
    val size: Double
)
