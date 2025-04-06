package es.rlujancreations.home.presentation.navigation.layouts

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.rlujancreations.core.presentation.IconDisplay
import es.rlujancreations.home.presentation.navigation.NavigationItem

/**
 * Created by Raúl L.C. on 6/4/25.
 */
@Composable
fun NavigationRailLayout(
    navigationItems: List<NavigationItem>,
    content: @Composable (PaddingValues) -> Unit,
) {
    Row {
        NavigationRail {
            Spacer(modifier = Modifier.height(16.dp))
            navigationItems.forEach { item ->
                NavigationRailItem(
                    selected = item.selected,
                    onClick = item.onClick,
                    label = { item.label() },
                    icon = { IconDisplay(item.icon()) },
                )
            }
        }
        Scaffold { paddingValues ->
            content(paddingValues)
        }
    }
}
