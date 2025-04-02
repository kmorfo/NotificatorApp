package es.rlujancreations.auth.presentation.register

import es.rlujancreations.core.presentation.UiText

/**
 * Created by Raúl L.C. on 1/4/25.
 */
sealed interface RegisterEvent {
    data object RegistrationSuccess : RegisterEvent

    data class Error(val error: UiText) : RegisterEvent
}
