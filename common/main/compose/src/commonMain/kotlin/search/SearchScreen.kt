package search

import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import di.Inject
import navigation.NavigationTree
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun SearchScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = {
        SearchViewModel(
            gamesRepository = Inject.instance()
        )
    }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState()
        val viewAction = viewModel.viewActions().observeAsState()

        SearchView(viewState = viewState.value) { event ->
            viewModel.obtainEvent(viewEvent = event)
        }

        when (viewAction.value) {
            is SearchAction.ShowGameDetail -> rootController.findRootController()
                .present(NavigationTree.Main.Game.name)
            null -> {}
        }
    }
}