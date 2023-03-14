package login.models

sealed class LoginAction{
    object OpenRegistrationScreen: LoginAction()
    object OpenForgotPasswordScreen: LoginAction()
    object MainFlow : LoginAction()
}
