package es.rlujancreations.home.presentation.navigation

import androidx.compose.runtime.Composable
import es.rlujancreations.core.presentation.AppIcon

/**
 * Created by Raúl L.C. on 12/3/25.
 */

data class NavigationItem(
    val selected: Boolean = false,
    val onClick: () -> Unit = {},
    val label: @Composable () -> Unit,
    val icon: @Composable () -> AppIcon,
)
