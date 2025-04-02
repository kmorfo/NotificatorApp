package es.rlujancreations.auth.presentation.register

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rlujancreations.auth.domain.validator.UserDataValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import notificatorapp.auth.presentation.generated.resources.Res
import notificatorapp.auth.presentation.generated.resources.invalid_email
import notificatorapp.auth.presentation.generated.resources.username_error

class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
) : ViewModel() {
    private var hasLoadedInitialData = false
    val snackbarHostState = SnackbarHostState()

    private val _state = MutableStateFlow(RegisterState())
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
                initialValue = RegisterState(),
            )

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.OnUsernameChange -> {
                val isEmailValid = userDataValidator.isValidEmail(state.value.email)
                val isValidUsername = userDataValidator.isValidUsername(action.username)
                _state.update {
                    it.copy(
                        username = action.username,
                        usernameError = if (isValidUsername) null else Res.string.username_error,
                        canRegister =
                            isEmailValid && it.password.isNotEmpty() &&
                                it.passwordValidationState.isValidPassword &&
                                it.passwordConfirmationState &&
                                isValidUsername &&
                                !it.isRegistering,
                    )
                }
            }

            is RegisterAction.OnEmailChange -> {
                val isEmailValid = userDataValidator.isValidEmail(action.email)
                val isValidUsername = userDataValidator.isValidUsername(state.value.username)

                _state.update {
                    it.copy(
                        email = action.email,
                        emailError = if (isEmailValid) null else Res.string.invalid_email,
                        canRegister =
                            isEmailValid && it.password.isNotEmpty() &&
                                it.passwordValidationState.isValidPassword &&
                                it.passwordConfirmationState &&
                                isValidUsername &&
                                !it.isRegistering,
                    )
                }
            }

            is RegisterAction.OnPasswordChange -> {
                val isEmailValid = userDataValidator.isValidEmail(state.value.email)
                val isValidPassword = userDataValidator.validatePassword(action.password)
                val isValidUsername = userDataValidator.isValidUsername(state.value.username)

                _state.update {
                    it.copy(
                        password = action.password,
                        passwordValidationState = isValidPassword,
                        canRegister =
                            isEmailValid && isValidPassword.isValidPassword &&
                                (it.password == it.passwordConfirmation) &&
                                isValidUsername &&
                                !it.isRegistering,
                    )
                }
            }

            is RegisterAction.OnPasswordConfirmationChange -> {
                val isEmailValid = userDataValidator.isValidEmail(state.value.email)
                val passwordConfirmationState = state.value.password == action.passwordConfirmation
                val isValidUsername = userDataValidator.isValidUsername(state.value.username)

                _state.update {
                    it.copy(
                        passwordConfirmation = action.passwordConfirmation,
                        passwordConfirmationState = passwordConfirmationState,
                        canRegister =
                            passwordConfirmationState && isEmailValid &&
                                it.passwordValidationState.isValidPassword &&
                                isValidUsername &&
                                !it.isRegistering,
                    )
                }
            }

            RegisterAction.OnRegisterClick -> register()

            RegisterAction.OnTogglePasswordVisibility -> {
                _state.update {
                    it.copy(
                        isPasswordVisible = !it.isPasswordVisible,
                    )
                }
            }

            RegisterAction.OnTogglePasswordConfirmationVisibility -> {
                _state.update {
                    it.copy(
                        isPasswordConfirmationVisible = !it.isPasswordConfirmationVisible,
                    )
                }
            }

            else -> Unit
        }
    }

    private fun register() {
    }
}
