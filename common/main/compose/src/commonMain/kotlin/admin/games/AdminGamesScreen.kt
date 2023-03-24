package admin.games

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import di.Inject
import games.AdminGamesViewModel
import games.models.AdminGamesAction
import games.models.AdminGamesEvent
import navigation.NavigationTree
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun AdminGamesScreen() {

    val rootController = LocalRootController.current

    StoredViewModel(factory = {
        AdminGamesViewModel(
            gamesRepository = Inject.instance()
        )
    }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState().value
        val viewAction = viewModel.viewActions().observeAsState().value

        AdminGamesView(
            viewState = viewState
        ) { event ->
            viewModel.obtainEvent(viewEvent = event)
        }

        when (viewAction) {
            is AdminGamesAction.ShowAddGame -> rootController.findRootController()
                .present(screen = NavigationTree.Admin.CreateGame.name)
            null -> {}
        }

        LaunchedEffect(key1 = Unit) {
            viewModel.obtainEvent(viewEvent = AdminGamesEvent.ViewInited)
        }
    }
}