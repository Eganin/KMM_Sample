package createtGame.models

data class CreateGameViewState(
    val title: String,
    val description: String,
    val size: String,
    val version: String,
    val isSending: Boolean
)
