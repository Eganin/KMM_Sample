package createGame

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import createtGame.CreateGameViewModel
import createtGame.models.CreateGameAction
import createtGame.models.CreateGameEvent
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import widgets.ActionButton
import widgets.CommonTextField

@Composable
fun CreateGameScreen() {
    val rootController = LocalRootController.current

    StoredViewModel(factory = { CreateGameViewModel() }) { viewModel ->
        val viewState = viewModel.viewStates().observeAsState().value
        val viewAction = viewModel.viewActions().observeAsState().value

        Column(modifier = Modifier.padding(16.dp)) {
            CommonTextField(
                text = viewState.title,
                hint = "Game title",
                enabled = !viewState.isSending
            ) {
                viewModel.obtainEvent(viewEvent = CreateGameEvent.TitleChanged(it))
            }

            Spacer(modifier = Modifier.height(16.dp))

            CommonTextField(
                text = viewState.description,
                hint = "Game description",
                enabled = !viewState.isSending
            ) {
                viewModel.obtainEvent(viewEvent = CreateGameEvent.DescriptionChanged(it))
            }

            Spacer(modifier = Modifier.height(16.dp))

            CommonTextField(
                text = viewState.version,
                hint = "Game version",
                enabled = !viewState.isSending
            ) {
                viewModel.obtainEvent(viewEvent = CreateGameEvent.VersionChanged(it))
            }

            Spacer(modifier = Modifier.height(16.dp))

            CommonTextField(
                text = viewState.size,
                hint = "Game size",
                enabled = !viewState.isSending
            ) {
                viewModel.obtainEvent(viewEvent = CreateGameEvent.SizeChanged(it))
            }

            Spacer(modifier = Modifier.height(16.dp))

            ActionButton(
                title = "Save Changes",
                enabled = !viewState.isSending
            ){
                viewModel.obtainEvent(viewEvent = CreateGameEvent.SubmitChanges)
            }
        }

        when (viewAction) {
            is CreateGameAction.CloseScreen -> rootController.popBackStack()
            null -> {}
        }
    }
}