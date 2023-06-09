package login.models

sealed class LoginEvent {
    data class EmailChanged(val value: String) : LoginEvent()

    data class PasswordChanged(val value: String) : LoginEvent()

    object LoginClick : LoginEvent()

    object RegistrationClick : LoginEvent()

    object ForgotClick : LoginEvent()

    object PasswordShowClick : LoginEvent()
}
