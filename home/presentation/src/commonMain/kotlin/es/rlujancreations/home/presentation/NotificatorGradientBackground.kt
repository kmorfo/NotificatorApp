package es.rlujancreations.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import es.rlujancreations.core.presentation.Shapes

/**
 * Created by Raúl L.C. on 8/4/25.
 */

@Composable
fun NotificatorGradientBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier.background(
            brush = Brush.linearGradient(
                colors = listOf(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.secondary,
                ),
            ),
            shape = Shapes.medium,
        ).padding(2.dp),
        contentAlignment = androidx.compose.ui.Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.background,
                    shape = Shapes.medium,
                ).padding(9.dp),
        ) {
            content()
        }
    }
}
