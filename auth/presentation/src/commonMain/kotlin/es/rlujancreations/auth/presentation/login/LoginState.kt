package es.rlujancreations.auth.presentation.login

import org.jetbrains.compose.resources.StringResource

data class LoginState(
    val email: String = "",
    val emailError: StringResource? = null,
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val canLogin: Boolean = false,
    val isLoggingIn: Boolean = false,
)
