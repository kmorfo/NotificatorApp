package es.rlujancreations.auth.presentation.recovery

data class RecoveryState(
    val paramOne: String = "default",
    val paramTwo: List<String> = emptyList(),
)
