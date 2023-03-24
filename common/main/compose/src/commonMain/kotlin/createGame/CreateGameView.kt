package createGame

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmm_sample.core.Res
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
            hint = Res.string.game_title_hint,
            enabled = !viewState.isSending
        ) {
            eventHandler.invoke(CreateGameEvent.TitleChanged(it))
        }

        Spacer(modifier = Modifier.height(16.dp))

        CommonTextField(
            text = viewState.description,
            hint = Res.string.game_description_hint,
            enabled = !viewState.isSending
        ) {
            eventHandler.invoke(CreateGameEvent.DescriptionChanged(it))
        }

        Spacer(modifier = Modifier.height(16.dp))

        CommonTextField(
            text = viewState.version,
            hint = Res.string.game_version_hint,
            enabled = !viewState.isSending
        ) {
            eventHandler.invoke(CreateGameEvent.VersionChanged(it))
        }

        Spacer(modifier = Modifier.height(16.dp))

        CommonTextField(
            text = viewState.size,
            hint = Res.string.game_size_hint,
            enabled = !viewState.isSending
        ) {
            eventHandler.invoke(CreateGameEvent.SizeChanged(it))
        }

        Spacer(modifier = Modifier.height(16.dp))

        ActionButton(
            title = Res.string.save_changes_button,
            enabled = !viewState.isSending
        ) {
            eventHandler.invoke(CreateGameEvent.SubmitChanges)
        }
    }
}