package es.rlujancreations.home.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
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
    var containerSize by remember { mutableStateOf(IntSize(0, 0)) }
    val density = LocalDensity.current

    val dpSize = with(density) {
        DpSize(containerSize.width.toDp(), containerSize.height.toDp())
    }

    val layoutType = forceLayoutType ?: determineLayoutType(dpSize.width, dpSize.height)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged { newSize ->
                containerSize = newSize
            },
    ) {
        when (layoutType) {
            NavigationLayoutType.NAVIGATION_DRAWER -> NavigationDrawerLayout(
                navigationItems,
                content,
            )

            NavigationLayoutType.NAVIGATION_RAIL -> NavigationRailLayout(navigationItems, content)
            NavigationLayoutType.BOTTOM_NAVIGATION -> BottomNavigationLayout(
                navigationItems,
                content,
            )
        }
    }

}

fun determineLayoutType(width: Dp, height: Dp): NavigationLayoutType {
    val isLandscape = width > height

    return when {
        width >= 840.dp -> NavigationLayoutType.NAVIGATION_DRAWER
        width >= 600.dp -> NavigationLayoutType.NAVIGATION_RAIL
        isLandscape -> NavigationLayoutType.NAVIGATION_RAIL
        else -> NavigationLayoutType.BOTTOM_NAVIGATION
    }
}
