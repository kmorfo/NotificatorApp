package es.rlujancreations.home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import es.rlujancreations.core.presentation.AppIcon
import es.rlujancreations.core.presentation.IconDisplay
import es.rlujancreations.core.presentation.WindowWidthSizeClass
import es.rlujancreations.core.presentation.getScreenDimensions

/**
 * Created by Raúl L.C. on 8/4/25.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NotificatorToolBar(
    modifier: Modifier = Modifier,
    notificationItems: List<NotificatorToolBarItem> = emptyList(),
) {
    val screenType = getScreenDimensions().windowWidthSizeClass
    FlowRow(
        modifier = modifier,
        verticalArrangement = if (screenType == WindowWidthSizeClass.Compact)
            Arrangement.Center else Arrangement.SpaceBetween,
        horizontalArrangement = if (screenType == WindowWidthSizeClass.Compact)
            Arrangement.End else Arrangement.Center,
    ) {
        notificationItems.forEach { item ->
            IconDisplay(
                appIcon = item.icon,
                contentDescription = item.title,
                tint = item.tint ?: MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .clickable(onClick = item.onClick)
                    .size(56.dp)
                    .padding(8.dp),
            )
        }
    }
}

data class NotificatorToolBarItem(
    val title: String,
    val icon: AppIcon,
    val tint: Color? = null,
    val onClick: () -> Unit = {},
)
