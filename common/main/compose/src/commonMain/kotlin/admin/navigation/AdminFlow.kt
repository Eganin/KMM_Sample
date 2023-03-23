package admin.navigation

import admin.events.AdminEventsScreen
import admin.games.AdminGamesScreen
import navigation.NavigationTree
import navigation.tabs.BottomConfiguration
import navigation.tabs.EventsTab
import navigation.tabs.GamesTab
import ru.alexgladkov.odyssey.compose.extensions.bottomNavigation
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.extensions.tab
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder

fun RootComposeBuilder.adminFlow() {
    bottomNavigation(
        name = NavigationTree.Admin.Dashboard.name,
        tabsNavModel = BottomConfiguration()
    ) {
        tab(GamesTab()) {
            screen(name = NavigationTree.Admin.Games.name) {
                AdminGamesScreen()
            }
        }

        tab(EventsTab()) {
            screen(name = NavigationTree.Admin.Events.name) {
                AdminEventsScreen()
            }
        }
    }
}