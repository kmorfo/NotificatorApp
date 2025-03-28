package es.rlujancreations.onboarding.presentation.login

import es.rlujancreations.core.presentation.UiText

/**
 * Created by Raúl L.C. on 27/3/25.
 */
sealed interface LoginEvent {
    data class Error(val error: UiText) : LoginEvent
    data object LoginSuccess : LoginEvent
}
