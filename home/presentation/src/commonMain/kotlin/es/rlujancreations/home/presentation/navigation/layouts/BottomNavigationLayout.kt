package es.rlujancreations.home.presentation.navigation.layouts

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import es.rlujancreations.core.presentation.IconDisplay
import es.rlujancreations.home.presentation.navigation.NavigationItem

/**
 * Created by Raúl L.C. on 6/4/25.
 */
@Composable
fun BottomNavigationLayout(
    navigationItems: List<NavigationItem>,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                navigationItems.forEach { item ->
                    NavigationBarItem(
                        selected = item.selected,
                        onClick = item.onClick,
                        label = { item.label() },
                        icon = { IconDisplay(item.icon()) },
                    )
                }
            }
        },
    ) { paddingValues ->
        content(paddingValues)
    }
}
