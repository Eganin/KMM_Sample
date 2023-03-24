package createGame

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import createGame.models.CreateGameEvent
import createGame.models.CreateGameViewState
import widgets.ActionButton
import widgets.CommonTextField

@Composable
fun CreateGameView(
    viewState: CreateGameViewState,
    modifier: Modifier = Modifier,
    eventHandler: (CreateGameEvent) -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        CommonTextField(
            text = viewState.title,
            hint = "Game title",
            enabled = !viewState.isSending
        ) {
            eventHandler.invoke(CreateGameEvent.TitleChanged(it))
        }

        Spacer(modifier = Modifier.height(16.dp))

        CommonTextField(
            text = viewState.description,
            hint = "Game description",
            enabled = !viewState.isSending
        ) {
            eventHandler.invoke(CreateGameEvent.DescriptionChanged(it))
        }

        Spacer(modifier = Modifier.height(16.dp))

        CommonTextField(
            text = viewState.version,
            hint = "Game version",
            enabled = !viewState.isSending
        ) {
            eventHandler.invoke(CreateGameEvent.VersionChanged(it))
        }

        Spacer(modifier = Modifier.height(16.dp))

        CommonTextField(
            text = viewState.size,
            hint = "Game size",
            enabled = !viewState.isSending
        ) {
            eventHandler.invoke(CreateGameEvent.SizeChanged(it))
        }

        Spacer(modifier = Modifier.height(16.dp))

        ActionButton(
            title = "Save Changes",
            enabled = !viewState.isSending
        ) {
            eventHandler.invoke(CreateGameEvent.SubmitChanges)
        }
    }
}