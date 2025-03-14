package es.rlujancreations.core.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import notificatorapp.core.presentation.generated.resources.Res
import notificatorapp.core.presentation.generated.resources.ic_apps
import notificatorapp.core.presentation.generated.resources.ic_channel
import notificatorapp.core.presentation.generated.resources.ic_devices
import notificatorapp.core.presentation.generated.resources.ic_logo
import notificatorapp.core.presentation.generated.resources.ic_project
import notificatorapp.core.presentation.generated.resources.ic_send
import notificatorapp.core.presentation.generated.resources.ic_task
import org.jetbrains.compose.resources.painterResource

/**
 * Created by Raúl L.C. on 12/3/25.
 */
sealed class AppIcon {
    data class VectorIcon(val imageVector: ImageVector) : AppIcon()

    data class PainterIcon(val painter: Painter) : AppIcon()
}

@Composable
fun CheckIcon(): AppIcon {
    return AppIcon.VectorIcon(Icons.Default.Done)
}

@Composable
fun AppsIcon(): AppIcon {
    return AppIcon.PainterIcon(painterResource(Res.drawable.ic_apps))
}

@Composable
fun ChannelIcon(): AppIcon {
    return AppIcon.PainterIcon(painterResource(Res.drawable.ic_channel))
}

@Composable
fun DevicesIcon(): AppIcon {
    return AppIcon.PainterIcon(painterResource(Res.drawable.ic_devices))
}

@Composable
fun LogoIcon(): AppIcon {
    return AppIcon.PainterIcon(painterResource(Res.drawable.ic_logo))
}

@Composable
fun ProjectIcon(): AppIcon {
    return AppIcon.PainterIcon(painterResource(Res.drawable.ic_project))
}

@Composable
fun SendIcon(): AppIcon {
    return AppIcon.PainterIcon(painterResource(Res.drawable.ic_send))
}

@Composable
fun TaskIcon(): AppIcon {
    return AppIcon.PainterIcon(painterResource(Res.drawable.ic_task))
}

@Composable
fun IconDisplay(
    appIcon: AppIcon,
    contentDescription: String? = null,
    tint: Color = Color.Unspecified,
    modifier: Modifier = Modifier.size(32.dp),
) {
    when (appIcon) {
        is AppIcon.VectorIcon ->
            Icon(
                imageVector = appIcon.imageVector,
                contentDescription = contentDescription,
                tint = tint,
                modifier = modifier,
            )

        is AppIcon.PainterIcon ->
            Icon(
                painter = appIcon.painter,
                contentDescription = contentDescription,
                tint = Color.Unspecified,
                modifier = modifier,
            )
    }
}
