package es.rlujancreations.auth.presentation.recovery

import es.rlujancreations.core.presentation.UiText

/**
 * Created by Raúl L.C. on 1/4/25.
 */
sealed interface RecoveryEvent {
    data class Error(val error: UiText) : RecoveryEvent

    data object RecoverySentSuccess : RecoveryEvent
}
