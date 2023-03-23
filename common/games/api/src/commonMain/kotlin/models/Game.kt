package models

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val gameId: String,
    val title: String
)
