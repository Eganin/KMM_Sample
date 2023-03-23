package navigation

import forgot.ForgotScreen
import login.LoginScreen
import register.RegisterScreen
import ru.alexgladkov.odyssey.compose.extensions.flow
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.authFlow() {
    flow(name = navigation.NavigationTree.Auth.AuthFlow.name) {
        screen(name = navigation.NavigationTree.Auth.Login.name) {
            LoginScreen()
        }

        screen(name = navigation.NavigationTree.Auth.Register.name) {
            RegisterScreen()
        }

        screen(name = navigation.NavigationTree.Auth.Forgot.name) {
            ForgotScreen()
        }
    }
}