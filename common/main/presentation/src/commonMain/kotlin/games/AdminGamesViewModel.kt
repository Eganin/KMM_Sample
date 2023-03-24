package games

import GamesRepository
import com.adeo.kviewmodel.BaseSharedViewModel
import games.models.AdminGamesAction
import games.models.AdminGamesEvent
import games.models.AdminGamesViewState
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch

class AdminGamesViewModel(
    private val gamesRepository: GamesRepository
) : BaseSharedViewModel<AdminGamesViewState, AdminGamesAction, AdminGamesEvent>(
    initialState = AdminGamesViewState()
) {
    override fun obtainEvent(viewEvent: AdminGamesEvent) {
        when (viewEvent) {
            is AdminGamesEvent.AddGameClicked -> viewAction = AdminGamesAction.ShowAddGame
            is AdminGamesEvent.ViewInited -> fetchAllGames()
        }
    }

    private fun fetchAllGames() {
        viewModelScope.launch {
            viewState = try {
                val games = gamesRepository.fetchAllGames()
                viewState.copy(games = games.toImmutableList())
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.copy(games = persistentListOf())
            }
        }
    }
}