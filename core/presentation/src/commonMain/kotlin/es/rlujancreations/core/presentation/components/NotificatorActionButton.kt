package es.rlujancreations.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import es.rlujancreations.core.presentation.AppIcon
import es.rlujancreations.core.presentation.ButtonShapeFull
import es.rlujancreations.core.presentation.IconDisplay

/**
 * Created by Raúl L.C. on 31/3/25.
 */
@Composable
fun NotificatorActionButton(
    modifier: Modifier = Modifier,
    text: String,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    shape: CornerBasedShape = ButtonShapeFull,
    icon: AppIcon? = null,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors =
            ButtonDefaults.run {
                buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    disabledContentColor =
                        MaterialTheme.colorScheme.onBackground.copy(
                            alpha = 0.5f,
                        ),
                )
            },
        border =
            BorderStroke(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.onPrimary,
            ),
        shape = shape,
        modifier = modifier.height(IntrinsicSize.Min),
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                modifier =
                    Modifier
                        .size(24.dp)
                        .alpha(if (isLoading) 1f else 0f),
                strokeWidth = 1.5.dp,
                color = MaterialTheme.colorScheme.surface,
            )
            if (icon == null) {
                Text(
                    text = text,
                    modifier = Modifier.alpha(if (isLoading) 0f else 1f),
                    style = MaterialTheme.typography.bodyLarge,
                )
            } else {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = text,
                        modifier = Modifier.alpha(if (isLoading) 0f else 1f),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    if (!isLoading) {
                        IconDisplay(
                            appIcon = icon,
                            tint = MaterialTheme.colorScheme.surface,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NotificatorOutlinedActionButton(
    text: String,
    isLoading: Boolean = false,
    modifier: Modifier = Modifier,
    shape: CornerBasedShape = ButtonShapeFull,
    enabled: Boolean = true,
    icon: AppIcon? = null,
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        colors =
            ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.primary,
            ),
        border =
            BorderStroke(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.onBackground,
            ),
        shape = shape,
        modifier =
            modifier
                .height(IntrinsicSize.Min),
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                modifier =
                    Modifier
                        .size(24.dp)
                        .alpha(if (isLoading) 1f else 0f),
                strokeWidth = 1.5.dp,
            )
            if (icon == null) {
                Text(
                    text = text,
                    modifier = Modifier.alpha(if (isLoading) 0f else 1f),
                    style = MaterialTheme.typography.bodyLarge,
                )
            } else {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = text,
                        modifier = Modifier.alpha(if (isLoading) 0f else 1f),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    if (!isLoading) {
                        IconDisplay(
                            appIcon = icon,
                            tint = MaterialTheme.colorScheme.surface,
                        )
                    }
                }
            }
        }
    }
}
