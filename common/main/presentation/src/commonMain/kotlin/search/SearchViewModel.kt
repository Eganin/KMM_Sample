package search

import GamesRepository
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel : BaseSharedViewModel<SearchViewState, SearchAction, SearchEvent>(
    initialState = SearchViewState()
) {

    private val gamesRepository: GamesRepository = Inject.instance()
    private var searchJob: Job? = null

    override fun obtainEvent(viewEvent: SearchEvent) {
        when (viewEvent) {
            is SearchEvent.GameClicked -> showDetailedGame()
            is SearchEvent.QueryChanged -> searchGame(query = viewEvent.query)
        }
    }

    private fun showDetailedGame() {
        viewAction = SearchAction.ShowGameDetail
    }

    private fun searchGame(query: String) {
        searchJob = viewModelScope.launch {
            viewState = viewState.copy(query = query)
            searchJob?.cancel()
            delay(500)
            viewState = try {
                val gamesResponse = if (query.isEmpty()) {
                    gamesRepository.fetchAllGames()
                } else {
                    gamesRepository.searchGame(query = query)
                }
                viewState.copy(games = gamesResponse)
            } catch (e: Exception) {
                viewState.copy(games = emptyList())
            }
        }
    }
}