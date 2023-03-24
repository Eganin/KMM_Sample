package search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kmm_sample.core.Res
import theme.Theme

@Composable
fun SearchView(viewState: SearchViewState, eventHandler: (SearchEvent) -> Unit) {
    Column {
        TextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(56.dp),
            value = viewState.query,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFF1F2430),
                textColor = Color(0XFF696C75),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Theme.colors.highlightTextColor
            ),
            placeholder = {
                Text(
                    text = Res.string.search_game_placeholder,
                    color = Theme.colors.hintTextColor
                )
            },
            shape = RoundedCornerShape(10.dp),
            onValueChange = {
                eventHandler.invoke(SearchEvent.QueryChanged(it))
            })

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            viewState.games.forEach {
                item {
                    Text(
                        text = it.title,
                        color = Theme.colors.secondaryTextColor,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable {
                            eventHandler.invoke(SearchEvent.GameClicked)
                        }.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}