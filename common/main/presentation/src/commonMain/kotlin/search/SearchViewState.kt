package search

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import models.Game

data class SearchViewState(
    val query: String = "",
    val games: ImmutableList<Game> = persistentListOf()
)
