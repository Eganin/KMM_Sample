package login

import AuthRepository
import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject
import kotlinx.coroutines.launch
import login.models.LoginAction
import login.models.LoginEvent
import login.models.LoginViewState

class LoginViewModel : BaseSharedViewModel<LoginViewState, LoginAction, LoginEvent>(
    initialState = LoginViewState(email = "", password = "")
) {

    private val authRepository: AuthRepository = Inject.instance()

    override fun obtainEvent(viewEvent: LoginEvent) {
        when (viewEvent) {
            is LoginEvent.LoginClick -> sendLogin()
            is LoginEvent.EmailChanged -> obtainEmailChanged(viewEvent.value)
            is LoginEvent.PasswordChanged -> obtainPasswordChanged(viewEvent.value)
            is LoginEvent.ForgotClick -> openForgot()
            is LoginEvent.RegistrationClick -> openRegistration()
            is LoginEvent.PasswordShowClick -> changePasswordVisibility()
        }
    }

    private fun changePasswordVisibility() {
        viewState = viewState.copy(passwordHidden = !viewState.passwordHidden)
    }

    private fun sendLogin() {
        viewState = viewState.copy(isSending = true)
        viewModelScope.launch {
            viewState = try {
                val response =
                    authRepository.login(login = viewState.email, password = viewState.password)
                if (response.token.isNotEmpty()) {
                    viewState.copy(email = "", password = "", isSending = false)
                } else {
                    viewState.copy(isSending = false)
                }
            } catch (e: Exception) {
                viewState.copy(isSending = false)
            }
        }
    }

    private fun openForgot() {
        viewAction = LoginAction.OpenForgotPasswordScreen
    }

    private fun openRegistration() {
        viewAction = LoginAction.OpenRegistrationScreen
    }

    private fun obtainEmailChanged(value: String) {
        viewState = viewState.copy(
            email = value
        )
    }

    private fun obtainPasswordChanged(value: String) {
        viewState = viewState.copy(
            password = value
        )
    }
}