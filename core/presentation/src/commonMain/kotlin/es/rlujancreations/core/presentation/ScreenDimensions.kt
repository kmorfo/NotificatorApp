package es.rlujancreations.core.presentation

/**
 * Created by Raúl L.C. on 25/3/25.
 */
interface ScreenDimensions {
    val screenWidthDp: Int
    val screenHeightDp: Int
    val windowWidthSizeClass: WindowWidthSizeClass
}

sealed interface WindowWidthSizeClass {
    data object Compact : WindowWidthSizeClass

    data object Medium : WindowWidthSizeClass

    data object Expanded : WindowWidthSizeClass
}

expect fun getScreenDimensions(): ScreenDimensions
