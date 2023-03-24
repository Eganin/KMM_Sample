package navigation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.adeo.kviewmodel.odyssey.setupWithViewModels
import ru.alexgladkov.odyssey.compose.base.Navigator
import ru.alexgladkov.odyssey.compose.extensions.setupWithActivity
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalNavigator
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.configuration.DefaultModalConfiguration
import ru.alexgladkov.odyssey.core.configuration.DisplayType
import theme.AppTheme
import theme.Theme

fun ComponentActivity.setupThemedNavigation() {
    val rootController = RootComposeBuilder().apply {
        generateGraph(source = NavigationSource.Android)
    }.build()

    rootController.setupWithActivity(this)
    rootController.setupWithViewModels()

    setContent {
        AppTheme {
            val backgroundColor = Theme.colors.primaryBackground
            rootController.backgroundColor = backgroundColor

            CompositionLocalProvider(
                LocalRootController provides rootController
            ) {
                ModalNavigator(
                    configuration = DefaultModalConfiguration(
                        backgroundColor=Theme.colors.primaryBackground,
                        displayType = DisplayType.EdgeToEdge
                    )
                ) {
                    Navigator(startScreen = NavigationTree.Splash.SplashScreen.name)
                }
            }
        }
    }
}