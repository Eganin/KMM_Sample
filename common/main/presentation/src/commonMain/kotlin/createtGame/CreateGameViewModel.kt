package createtGame

import AuthRepository
import GamesRepository
import com.adeo.kviewmodel.BaseSharedViewModel
import createtGame.models.CreateGameAction
import createtGame.models.CreateGameEvent
import createtGame.models.CreateGameViewState
import di.Inject
import kotlinx.coroutines.launch
import models.CreateGameInfo

class CreateGameViewModel: BaseSharedViewModel<CreateGameViewState,CreateGameAction,CreateGameEvent>(
    initialState = CreateGameViewState(
        title = "",
        description = "",
        size = "",
        version = "",
        isSending = false
    )
) {

    private val authRepository: AuthRepository = Inject.instance()
    private val gamesRepository: GamesRepository = Inject.instance()
    override fun obtainEvent(viewEvent: CreateGameEvent) {
        when(viewEvent){
            is CreateGameEvent.TitleChanged -> viewState = viewState.copy(title = viewEvent.value)
            is CreateGameEvent.DescriptionChanged -> viewState = viewState.copy(description = viewEvent.value)
            is CreateGameEvent.VersionChanged -> viewState = viewState.copy(version = viewEvent.value)
            is CreateGameEvent.SizeChanged -> viewState = viewState.copy(size = viewEvent.value)
            is CreateGameEvent.SubmitChanges ->createGame()
        }
    }

    private fun createGame(){
        viewModelScope.launch {
            viewState = viewState.copy(isSending = true)
            try {
                val token = authRepository.fetchToken()
                println(CreateGameInfo(
                    title = viewState.title,
                    description = viewState.description,
                    version = viewState.version,
                    size = viewState.size.toDouble()
                ))
                gamesRepository.createGame(token=token, info = CreateGameInfo(
                    title = viewState.title,
                    description = viewState.description,
                    version = viewState.version,
                    size = viewState.size.toDouble()
                ))
                viewAction = CreateGameAction.CloseScreen
            }catch (e: Exception){
                viewState= viewState.copy(isSending = false)
            }
        }
    }
}