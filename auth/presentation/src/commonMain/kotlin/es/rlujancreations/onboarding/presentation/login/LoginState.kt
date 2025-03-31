package es.rlujancreations.onboarding.presentation.login

data class LoginState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val canLogin: Boolean = false,
    val isLoggingIn: Boolean = false,
)
