package es.rlujancreations.home.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import es.rlujancreations.core.presentation.WindowWidthSizeClass
import es.rlujancreations.core.presentation.getScreenDimensions
import es.rlujancreations.home.presentation.navigation.layouts.BottomNavigationLayout
import es.rlujancreations.home.presentation.navigation.layouts.NavigationDrawerLayout
import es.rlujancreations.home.presentation.navigation.layouts.NavigationRailLayout

/**
 * Created by Raúl L.C. on 6/4/25.
 */
@Composable
fun AdaptiveNavigationScaffold(
    navigationItems: List<NavigationItem>,
    content: @Composable (PaddingValues) -> Unit,
    forceLayoutType: NavigationLayoutType? = null,
) {
    val screenDimensions = getScreenDimensions()

    val layoutType =
        forceLayoutType ?: when (screenDimensions.windowWidthSizeClass) {
            WindowWidthSizeClass.Medium -> NavigationLayoutType.NAVIGATION_RAIL
            WindowWidthSizeClass.Expanded -> NavigationLayoutType.NAVIGATION_DRAWER
            else -> NavigationLayoutType.BOTTOM_NAVIGATION
        }

    when (layoutType) {
        NavigationLayoutType.NAVIGATION_DRAWER -> NavigationDrawerLayout(navigationItems, content)
        NavigationLayoutType.NAVIGATION_RAIL -> NavigationRailLayout(navigationItems, content)
        NavigationLayoutType.BOTTOM_NAVIGATION -> BottomNavigationLayout(navigationItems, content)
    }
}
