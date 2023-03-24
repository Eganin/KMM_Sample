package admin.games

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import games.models.AdminGamesEvent
import games.models.AdminGamesViewState
import theme.Theme

@Composable
fun AdminGamesView(
    viewState: AdminGamesViewState,
    modifier: Modifier = Modifier,
    eventHandler: (AdminGamesEvent) -> Unit
) {
    Column(modifier=modifier.fillMaxSize()) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Games List",
                fontSize = 28.sp,
                color = Theme.colors.secondaryTextColor
            )
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Theme.colors.primaryAction,
                modifier = Modifier.clickable {
                    eventHandler.invoke(AdminGamesEvent.AddGameClicked)
                }
            )
        }

        LazyColumn(modifier=modifier.fillMaxSize()) {
            viewState.games.forEach {
                item {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        text = it.title,
                        color = Theme.colors.secondaryTextColor,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}