package es.rlujancreations.notificatorapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector
import notificatorapp.composeapp.generated.resources.Res
import notificatorapp.composeapp.generated.resources.apps_title
import notificatorapp.composeapp.generated.resources.channels_title
import notificatorapp.composeapp.generated.resources.devices_title
import notificatorapp.composeapp.generated.resources.messages_title
import notificatorapp.composeapp.generated.resources.projects_title
import notificatorapp.composeapp.generated.resources.tasks_title
import org.jetbrains.compose.resources.StringResource

/**
 * Created by Raúl L.C. on 12/3/25.
 */
enum class NotificatorDestinations(
    val title: StringResource,
    val icon: ImageVector,
) {
    Projects(Res.string.projects_title, Icons.Default.Build),
    Applications(Res.string.apps_title, Icons.Default.Create),
    Devices(Res.string.devices_title, Icons.Default.Build),
    Channels(Res.string.channels_title, Icons.Default.Build),
    Messages(Res.string.messages_title, Icons.Default.Notifications),
    Tasks(Res.string.tasks_title, Icons.Default.Build),
}
