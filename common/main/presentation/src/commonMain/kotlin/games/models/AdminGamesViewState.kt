package games.models

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import models.Game

data class AdminGamesViewState(
    val games : ImmutableList<Game> = persistentListOf()
)
