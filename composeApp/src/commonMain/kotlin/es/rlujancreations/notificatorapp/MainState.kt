package es.rlujancreations.notificatorapp

/**
 * Created by Raúl L.C. on 20/3/25.
 */
data class MainState(
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = true,
    val hasSeenOnboarding: Boolean = false,
)
