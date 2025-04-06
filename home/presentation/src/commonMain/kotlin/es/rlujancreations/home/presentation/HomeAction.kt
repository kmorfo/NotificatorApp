package es.rlujancreations.home.presentation

/**
 * Created by Raúl L.C. on 17/3/25.
 */
sealed interface HomeAction {
    data object OnSettingsClick : HomeAction

    data object OnTokenExpired : HomeAction

    data class OnItemClicked(val index: Int) : HomeAction
}
