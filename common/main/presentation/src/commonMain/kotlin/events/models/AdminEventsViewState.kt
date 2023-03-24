package events.models

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class AdminEventsViewState(
    val events : ImmutableList<String> = persistentListOf()
)
