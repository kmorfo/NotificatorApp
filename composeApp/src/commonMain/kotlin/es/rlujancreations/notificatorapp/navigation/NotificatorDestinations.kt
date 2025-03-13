package es.rlujancreations.notificatorapp.navigation

import androidx.compose.runtime.Composable
import es.rlujancreations.core.presentation.AppIcon
import es.rlujancreations.core.presentation.AppsIcon
import es.rlujancreations.core.presentation.ChannelIcon
import es.rlujancreations.core.presentation.DevicesIcon
import es.rlujancreations.core.presentation.ProjectIcon
import es.rlujancreations.core.presentation.SendIcon
import es.rlujancreations.core.presentation.TaskIcon
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
    val icon: @Composable () -> AppIcon,
) {
    Projects(Res.string.projects_title, { ProjectIcon() }),
    Applications(Res.string.apps_title, { AppsIcon() }),
    Devices(Res.string.devices_title, { DevicesIcon() }),
    Channels(Res.string.channels_title, { ChannelIcon() }),
    Messages(Res.string.messages_title, { SendIcon() }),
    Tasks(Res.string.tasks_title, { TaskIcon() }),

}

