package navigation

import SplashScreen
import admin.navigation.adminFlow
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import createGame.CreateGameScreen
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

enum class NavigationSource {
    Desktop,
    Android,
    IOS
}

fun RootComposeBuilder.generateGraph(source: NavigationSource) {
    screen(name = NavigationTree.Splash.SplashScreen.name) {
        SplashScreen()
    }

    screen(name = NavigationTree.Main.Game.name) {
        Text(text = "Hello, Game Detail", color = Color.White)
    }

    authFlow()
    when (source) {
        NavigationSource.Android -> mainFlow()
        NavigationSource.Desktop -> {
            adminFlow()

            screen(name = NavigationTree.Admin.CreateGame.name) {
                CreateGameScreen()
            }
        }

        NavigationSource.IOS -> {}
    }
}