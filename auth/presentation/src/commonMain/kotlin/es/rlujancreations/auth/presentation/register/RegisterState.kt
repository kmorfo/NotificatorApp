package es.rlujancreations.auth.presentation.register

data class RegisterState(
    val paramOne: String = "default",
    val paramTwo: List<String> = emptyList(),
)
