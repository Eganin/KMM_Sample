package login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmm_sample.core.Res
import login.models.LoginEvent
import login.models.LoginViewState
import theme.Theme
import widgets.CommonTextField

@Composable
fun LoginView(
    state: LoginViewState,
    modifier: Modifier = Modifier,
    eventHandler: (LoginEvent) -> Unit
) {
    Column(
        modifier = modifier
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = Res.string.login_title,
            color = Theme.colors.thirdTextColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = Res.string.welcome_playzone,
            modifier = Modifier.padding(top = 15.dp),
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Theme.colors.hintTextColor
        )

        Spacer(modifier = Modifier.height(50.dp))

        CommonTextField(
            text = state.email,
            hint = Res.string.login_hint,
            enabled = !state.isSending
        ) {
            eventHandler.invoke(LoginEvent.EmailChanged(it))
        }

        Spacer(modifier = Modifier.height(24.dp))

        CommonTextField(
            text = state.password,
            hint = Res.string.password_hint,
            enabled = !state.isSending,
            isSecure = state.passwordHidden,
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        eventHandler.invoke(LoginEvent.PasswordShowClick)
                    },
                    imageVector = if (state.passwordHidden) {
                        Icons.Outlined.Clear
                    } else {
                        Icons.Outlined.Lock
                    },
                    contentDescription = Res.string.secure_icon_content_description,
                    tint = Theme.colors.hintTextColor
                )
            }
        ) {
            eventHandler.invoke(LoginEvent.PasswordChanged(it))
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = Res.string.forgot_password,
                color = Theme.colors.primaryAction,
                fontSize = 12.sp,
                modifier = Modifier.clickable {
                    eventHandler.invoke(LoginEvent.ForgotClick)
                })
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Theme.colors.primaryAction
            ),
            shape = RoundedCornerShape(10.dp),
            enabled = !state.isSending,
            onClick = {
                eventHandler.invoke(LoginEvent.LoginClick)
            }) {
            Text(
                text = Res.string.login_title,
                color = Theme.colors.primaryTextColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}