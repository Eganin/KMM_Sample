package home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import home.models.HomeAction
import navigation.NavigationTree
import ru.alexgladkov.odyssey.compose.extensions.push
import ru.alexgladkov.odyssey.compose.local.LocalRootController

@Composable
fun HomeScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { HomeViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState()
        val viewAction = viewModel.viewActions().observeAsState()

        HomeView(viewState = viewState.value, modifier = Modifier.fillMaxSize()) { event ->
            viewModel.obtainEvent(viewEvent = event)
        }

        when (viewAction.value) {
            is HomeAction.ShowUserProfile -> rootController.push(
                screen = NavigationTree.Main.Profile.name
            )
            null -> {}
        }
    }
}