package es.rlujancreations.auth.presentation.login

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rlujancreations.auth.domain.usecases.LoginUseCase
import es.rlujancreations.auth.domain.validator.UserDataValidator
import es.rlujancreations.core.domain.util.DataError
import es.rlujancreations.core.domain.util.onError
import es.rlujancreations.core.domain.util.onSuccess
import es.rlujancreations.core.presentation.UiText
import es.rlujancreations.core.presentation.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import notificatorapp.auth.presentation.generated.resources.Res
import notificatorapp.auth.presentation.generated.resources.error_email_password_incorrect
import notificatorapp.auth.presentation.generated.resources.invalid_email

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val userDataValidator: UserDataValidator,
) : ViewModel() {
    private var hasLoadedInitialData = false
    val snackbarHostState = SnackbarHostState()

    private val _state = MutableStateFlow(LoginState())
    val state =
        _state
            .onStart {
                if (!hasLoadedInitialData) {
                    /** Load initial data here **/
                    hasLoadedInitialData = true
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = LoginState(),
            )

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnEmailChange -> {
                _state.update {
                    it.copy(
                        email = action.email.trim(),
                        emailError =
                            if (userDataValidator.isValidEmail(email = action.email.trim())) {
                                null
                            } else {
                                Res.string.invalid_email
                            },
                        canLogin =
                            userDataValidator.isValidEmail(email = action.email.trim()) &&
                                it.password.isNotBlank(),
                    )
                }
            }

            is LoginAction.OnPasswordChange -> {
                _state.update {
                    it.copy(
                        password = action.password.trim(),
                        canLogin =
                            userDataValidator.isValidEmail(email = it.email.trim()) &&
                                it.password.isNotBlank(),
                    )
                }
            }

            LoginAction.OnLoginClick -> login()
            LoginAction.OnTogglePasswordVisibility -> {
                _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }

            else -> Unit
        }
    }

    private fun login() {
        _state.update { it.copy(isLoggingIn = true) }
        viewModelScope.launch {
            loginUseCase(
                email = state.value.email,
                password = state.value.password,
            ).onSuccess {
                eventChannel.send(LoginEvent.LoginSuccess)
            }.onError { error ->
                eventChannel.send(LoginEvent.Error(error.asUiText()))
            }
            _state.update { it.copy(isLoggingIn = false) }
        }
    }
}
