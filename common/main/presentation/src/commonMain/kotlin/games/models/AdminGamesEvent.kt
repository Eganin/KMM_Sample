package games.models

sealed class AdminGamesEvent{
    object AddGameClicked : AdminGamesEvent()

    object ViewInited:AdminGamesEvent()
}
