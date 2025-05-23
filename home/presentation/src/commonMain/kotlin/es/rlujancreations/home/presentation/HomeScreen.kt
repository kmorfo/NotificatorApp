package es.rlujancreations.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import es.rlujancreations.core.presentation.AppsIcon
import es.rlujancreations.core.presentation.ChannelIcon
import es.rlujancreations.core.presentation.CloseIcon
import es.rlujancreations.core.presentation.DevicesIcon
import es.rlujancreations.core.presentation.ObserveAsEvents
import es.rlujancreations.core.presentation.ProjectIcon
import es.rlujancreations.core.presentation.SendIcon
import es.rlujancreations.core.presentation.SettingsIcon
import es.rlujancreations.core.presentation.StringProvider
import es.rlujancreations.core.presentation.TaskIcon
import es.rlujancreations.core.presentation.WindowWidthSizeClass
import es.rlujancreations.core.presentation.getScreenDimensions
import es.rlujancreations.home.presentation.components.NotificatorGradientBackground
import es.rlujancreations.home.presentation.components.NotificatorToolBar
import es.rlujancreations.home.presentation.components.NotificatorToolBarItem
import es.rlujancreations.home.presentation.navigation.AdaptiveNavigationScaffold
import es.rlujancreations.home.presentation.navigation.NavigationItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import notificatorapp.home.presentation.generated.resources.Res
import notificatorapp.home.presentation.generated.resources.apps_title
import notificatorapp.home.presentation.generated.resources.channels_title
import notificatorapp.home.presentation.generated.resources.devices_title
import notificatorapp.home.presentation.generated.resources.messages_title
import notificatorapp.home.presentation.generated.resources.projects_title
import notificatorapp.home.presentation.generated.resources.tasks_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by Raúl L.C. on 17/3/25.
 */

@Composable
fun HomeScreenRoot(
    onSettingsClick: () -> Unit,
    onLogout: () -> Unit,
    viewModel: HomeViewModel = koinViewModel(),
) {
    HomeScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is HomeAction.OnSettingsClick -> onSettingsClick()
                else -> Unit
            }
            viewModel.onAction(action)
        },
        snackbarHostState = viewModel.snackbarHostState,
        events = viewModel.events,
        onLogout = onLogout,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    state: HomeState,
    events: Flow<HomeEvent>,
    snackbarHostState: SnackbarHostState,
    onAction: (HomeAction) -> Unit,
    onLogout: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val stringProvider: StringProvider = koinInject()

    val navigationItems =
        listOf(
            Res.string.projects_title,
            Res.string.apps_title,
            Res.string.devices_title,
            Res.string.channels_title,
            Res.string.messages_title,
            Res.string.tasks_title,
        ).mapIndexed { index, label ->
            NavigationItem(
                selected = index == state.selectedIndex,
                onClick = { onAction(HomeAction.OnItemClicked(index)) },
                label = { Text(stringResource(label)) },
                icon = {
                    when (index) {
                        0 -> ProjectIcon()
                        1 -> AppsIcon()
                        2 -> DevicesIcon()
                        3 -> ChannelIcon()
                        4 -> SendIcon()
                        5 -> TaskIcon()
                        else -> ProjectIcon()
                    }
                },
            )
        }

    ObserveAsEvents(events) { event ->
        when (event) {
            is HomeEvent.Error -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = event.error.asString(stringProvider),
                        duration = SnackbarDuration.Short,
                    )
                }
            }

            is HomeEvent.ErrorTokenExpired -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = event.error.asString(stringProvider),
                        duration = SnackbarDuration.Short,
                    )
                }
                onLogout()
            }

            is HomeEvent.Success -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(stringProvider),
                        duration = SnackbarDuration.Short,
                    )
                }
            }

            HomeEvent.Logout -> {
                onLogout()
            }
        }
    }
    AdaptiveNavigationScaffold(
        navigationItems = navigationItems,
        content = { paddingValues ->
            val screenType = getScreenDimensions().windowWidthSizeClass
            val notificationItems = listOf(
                NotificatorToolBarItem(
                    title = "Settings",
                    icon = SettingsIcon(),
                    onClick = { println("settings") },
                ),

                NotificatorToolBarItem(
                    title = "Logout",
                    icon = CloseIcon(),
                    tint = Color.Red,
                    onClick = { onAction(HomeAction.OnLogoutClick) },
                ),
            )
            if (screenType == WindowWidthSizeClass.Compact) {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                ) {
                    NotificatorToolBar(
                        modifier = Modifier.fillMaxSize().weight(0.08f),
                        notificationItems = notificationItems,
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth().height(1.dp)
                            .background(MaterialTheme.colorScheme.primary),
                    )
                    FlowRow(
                        modifier = Modifier.fillMaxSize().weight(0.9f).padding(8.dp),
                    ) {
                        NotificatorGradientBackground() {
                            Column {
                                Text(text = "Hola")
                                Text(text = "Hola")
                                Text(text = "Hola")
                            }
                        }
                    }
                }
            } else {
                Row(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                ) {

                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .weight(0.9f)
                            .fillMaxSize(),
                    ) {
                        FlowRow(
                            modifier = Modifier.fillMaxSize().padding(8.dp),
                        ) {
                            NotificatorGradientBackground() {
                                Column {
                                    Text(text = "Hola")
                                    Text(text = "Hola")
                                    Text(text = "Hola")
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier.fillMaxHeight().width(1.dp)
                            .background(MaterialTheme.colorScheme.primary),
                    )
                    NotificatorToolBar(
                        modifier = Modifier.fillMaxSize().weight(0.1f),
                        notificationItems = notificationItems,
                    )
                }
            }
        },
    )
}
