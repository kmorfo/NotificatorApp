package es.rlujancreations.auth.presentation.recovery

sealed interface RecoveryAction {
    data class OnEmailChanged(val email: String) : RecoveryAction

    data object OnRecoveryClick : RecoveryAction

    data object OnLoginClick : RecoveryAction
}
