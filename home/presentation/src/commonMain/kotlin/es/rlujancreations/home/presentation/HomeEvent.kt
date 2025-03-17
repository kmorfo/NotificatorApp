package es.rlujancreations.home.presentation

import es.rlujancreations.core.presentation.UiText

/**
 * Created by Raúl L.C. on 17/3/25.
 */
sealed interface HomeEvent {
    data class Error(val error: UiText) : HomeEvent
    data class ErrorTokenExpired(val error: UiText) : HomeEvent
    data class Success(val message: UiText) : HomeEvent
}
