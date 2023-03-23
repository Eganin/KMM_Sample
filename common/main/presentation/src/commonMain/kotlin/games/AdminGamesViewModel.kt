package games

import GamesRepository
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import games.models.AdminGamesAction
import games.models.AdminGamesEvent
import games.models.AdminGamesViewState
import kotlinx.coroutines.launch

class AdminGamesViewModel : BaseSharedViewModel<AdminGamesViewState, AdminGamesAction, AdminGamesEvent>(
    initialState = AdminGamesViewState()
) {
    private val gamesRepository: GamesRepository = Inject.instance()

    override fun obtainEvent(viewEvent: AdminGamesEvent) {
        when (viewEvent) {
            is AdminGamesEvent.AddGameClicked -> viewAction = AdminGamesAction.ShowAddGame
            is AdminGamesEvent.ViewInited -> fetchAllGames()
        }
    }

    private fun fetchAllGames() {
        viewModelScope.launch {
            viewState = try {
                val games = gamesRepository.searchGame("")
                viewState.copy(games = games)
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.copy(games = emptyList())
            }
        }
    }
}