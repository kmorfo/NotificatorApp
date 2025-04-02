package es.rlujancreations.auth.presentation.register

sealed interface RegisterAction {
    data class OnEmailChange(val email: String) : RegisterAction

    data class OnUsernameChange(val username: String) : RegisterAction

    data class OnPasswordChange(val password: String) : RegisterAction

    data class OnPasswordConfirmationChange(val passwordConfirmation: String) : RegisterAction

    data object OnTogglePasswordVisibility : RegisterAction

    data object OnTogglePasswordConfirmationVisibility : RegisterAction

    data object OnLoginClick : RegisterAction

    data object OnRegisterClick : RegisterAction
}
