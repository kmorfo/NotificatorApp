package es.rlujancreations.auth.presentation.register

import es.rlujancreations.auth.domain.validator.PasswordValidationState
import org.jetbrains.compose.resources.StringResource

data class RegisterState(
    val email: String = "",
    val emailError: StringResource? = null,
    val username: String = "",
    val usernameError: StringResource? = null,
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val passwordValidationState: PasswordValidationState = PasswordValidationState(),
    val passwordConfirmation: String = "",
    val isPasswordConfirmationVisible: Boolean = false,
    val passwordConfirmationState: Boolean = false,
    val isRegistering: Boolean = false,
    val canRegister: Boolean = false,
)
