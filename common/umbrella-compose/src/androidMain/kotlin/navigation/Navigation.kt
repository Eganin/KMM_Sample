package navigation

import SplashScreen
import androidx.compose.runtime.Composable
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.generateGraph() {
    screen(name = NavigationTree.Splash.SplashScreen.name){
        SplashScreen()
    }
    authFlow()
}