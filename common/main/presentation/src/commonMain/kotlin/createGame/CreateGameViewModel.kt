package createGame

import AuthRepository
import GamesRepository
import com.adeo.kviewmodel.BaseSharedViewModel
import createGame.models.CreateGameAction
import createGame.models.CreateGameEvent
import createGame.models.CreateGameViewState
import kotlinx.coroutines.launch
import models.CreateGameInfo

class CreateGameViewModel(
    private val authRepository: AuthRepository,
    private val gamesRepository: GamesRepository
) : BaseSharedViewModel<CreateGameViewState, CreateGameAction, CreateGameEvent>(
    initialState = CreateGameViewState()
) {
    override fun obtainEvent(viewEvent: CreateGameEvent) {
        when (viewEvent) {
            is CreateGameEvent.TitleChanged -> viewState = viewState.copy(title = viewEvent.value)
            is CreateGameEvent.DescriptionChanged -> viewState =
                viewState.copy(description = viewEvent.value)
            is CreateGameEvent.VersionChanged -> viewState =
                viewState.copy(version = viewEvent.value)
            is CreateGameEvent.SizeChanged -> viewState = viewState.copy(size = viewEvent.value)
            is CreateGameEvent.SubmitChanges -> createGame()
        }
    }

    private fun createGame() {
        viewModelScope.launch {
            viewState = viewState.copy(isSending = true)
            try {
                val token = authRepository.fetchToken()

                gamesRepository.createGame(
                    token = token,
                    info = CreateGameInfo(
                        title = viewState.title,
                        description = viewState.description,
                        version = viewState.version,
                        size = viewState.size.toDouble()
                    )
                )
                viewAction = CreateGameAction.CloseScreen
            } catch (e: Exception) {
                viewState = viewState.copy(isSending = false)
            }
        }
    }
}