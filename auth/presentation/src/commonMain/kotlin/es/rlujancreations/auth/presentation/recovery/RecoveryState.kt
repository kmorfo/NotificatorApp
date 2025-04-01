package es.rlujancreations.auth.presentation.recovery

import org.jetbrains.compose.resources.StringResource

data class RecoveryState(
    val email: String = "",
    val emailError: StringResource? = null,
    val canRecovery: Boolean = false,
    val isSendingRecovery: Boolean = false,
)
