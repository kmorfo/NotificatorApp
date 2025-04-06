package es.rlujancreations.home.presentation.navigation.layouts

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
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
fun NavigationDrawerLayout(
    navigationItems: List<NavigationItem>,
    content: @Composable (PaddingValues) -> Unit,
) {
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(modifier = Modifier.width(240.dp)) {
                Spacer(modifier = Modifier.height(16.dp))
                navigationItems.forEach { item ->
                    NavigationDrawerItem(
                        selected = item.selected,
                        onClick = item.onClick,
                        label = { item.label() },
                        icon = { IconDisplay(item.icon()) },
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                    )
                }
            }
        },
    ) {
        Scaffold { paddingValues ->
            content(paddingValues)
        }
    }
}
