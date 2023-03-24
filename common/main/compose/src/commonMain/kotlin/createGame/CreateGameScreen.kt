package createGame

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import createGame.models.CreateGameAction
import di.Inject
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun CreateGameScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = {
        CreateGameViewModel(
            authRepository = Inject.instance(),
            gamesRepository = Inject.instance()
        )
    }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState().value
        val viewAction = viewModel.viewActions().observeAsState().value

        CreateGameView(
            viewState = viewState,
            modifier = Modifier.fillMaxSize()
        ) { event ->
            viewModel.obtainEvent(viewEvent = event)
        }

        when (viewAction) {
            is CreateGameAction.CloseScreen -> rootController.popBackStack()
            null -> {}
        }
    }
}