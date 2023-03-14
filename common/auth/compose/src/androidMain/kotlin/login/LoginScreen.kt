package login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel
import login.models.LoginEvent
import theme.Theme

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    StoredViewModel(factory = { LoginViewModel() }) { viewModel ->
        val state = viewModel.viewStates().observeAsState()
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login Now",
                color = Theme.colors.thirdTextColor,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Welcome back to PlayZone! Enter your email addres and your password to enjoy the latest features of PlayZone",
                modifier = Modifier.padding(top = 15.dp),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = Theme.colors.hintTextColor
            )

            Spacer(modifier = Modifier.height(50.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                value = state.value.email,
                enabled = !state.value.isSending,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFF1F2430),
                    textColor = Color(0XFF696C75),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Theme.colors.highlightTextColor
                ),
                placeholder = {
                    Text(
                        text = "Your login",
                        color = Theme.colors.hintTextColor
                    )
                },
                shape = RoundedCornerShape(10.dp),
                onValueChange = {
                    viewModel.obtainEvent(LoginEvent.EmailChanged(it))
                })

            Spacer(modifier = Modifier.height(24.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                value = state.value.password,
                enabled = !state.value.isSending,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFF1F2430),
                    textColor = Color(0XFF696C75),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Theme.colors.highlightTextColor
                ),
                placeholder = {
                    Text(
                        text = "Your password",
                        color = Theme.colors.hintTextColor
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            viewModel.obtainEvent(LoginEvent.PasswordShowClick)
                        },
                        imageVector = if (state.value.passwordHidden) {
                            Icons.Outlined.Clear
                        } else {
                            Icons.Outlined.Lock
                        },
                        contentDescription = null,
                        tint = Theme.colors.hintTextColor
                    )
                },
                visualTransformation = if (state.value.passwordHidden) {
                    PasswordVisualTransformation()
                } else VisualTransformation.None,
                shape = RoundedCornerShape(10.dp),
                onValueChange = {
                    viewModel.obtainEvent(LoginEvent.PasswordChanged(it))
                })

            Spacer(modifier = Modifier.height(84.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Theme.colors.primaryAction
                ),
                shape = RoundedCornerShape(10.dp),
                enabled = !state.value.isSending,
                onClick = {
                    viewModel.obtainEvent(LoginEvent.LoginClick)
                }) {
                Text(
                    text = "Login Now",
                    color = Theme.colors.primaryTextColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}