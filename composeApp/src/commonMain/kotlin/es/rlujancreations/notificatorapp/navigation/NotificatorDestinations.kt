package es.rlujancreations.notificatorapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by Raúl L.C. on 12/3/25.
 */
enum class NotificatorDestinations(
    val title: String,
    val icon: ImageVector,
) {
    Projects("Projects", Icons.Default.Build),
    Applications("Apps", Icons.Default.Create),
    Devices("Devices", Icons.Default.Build),
    Channels("Channels", Icons.Default.Build),
    Messages("Messages", Icons.Default.Notifications),
    Tasks("Tasks", Icons.Default.Build),
}
