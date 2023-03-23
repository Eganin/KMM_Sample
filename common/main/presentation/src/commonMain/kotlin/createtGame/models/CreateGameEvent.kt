package createtGame.models

sealed class CreateGameEvent{
    data class TitleChanged(val value: String): CreateGameEvent()
    data class DescriptionChanged(val value: String): CreateGameEvent()
    data class VersionChanged(val value: String): CreateGameEvent()
    data class SizeChanged(val value: String): CreateGameEvent()

    object SubmitChanges: CreateGameEvent()
}
