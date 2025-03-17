package es.rlujancreations.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import es.rlujancreations.core.presentation.ObserveAsEvents
import es.rlujancreations.core.presentation.StringProvider
import es.rlujancreations.core.presentation.components.MySnackbar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by Raúl L.C. on 17/3/25.
 */

@Composable
fun HomeScreenRoot(
    onSettingsClick: () -> Unit,
    onTokenExpired: () -> Unit,
    viewModel: HomeViewModel = koinViewModel(),
) {

    HomeScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is HomeAction.OnSettingsClick -> onSettingsClick()
                is HomeAction.OnTokenExpired -> onTokenExpired()
            }
            viewModel.onAction(action)
        },
        snackbarHostState = viewModel.snackbarHostState,
        events = viewModel.events,
        onTokenExpired = onTokenExpired,
    )
}

@Composable
fun HomeScreen(
    state: HomeState,
    events: Flow<HomeEvent>,
    snackbarHostState: SnackbarHostState,
    onAction: (HomeAction) -> Unit,
    onTokenExpired: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val stringProvider: StringProvider = koinInject()

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
                onTokenExpired()
            }

            is HomeEvent.Success -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(stringProvider),
                        duration = SnackbarDuration.Short,
                    )
                }
            }
        }
    }
    //            NavigationWrapperUI {
//                ScaffoldAppContent()
//            }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { snackbarData ->
                MySnackbar(snackbarData)
            }
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Text("Hola desde scaffold")
        }
    }

}

//@OptIn(ExperimentalMaterial3AdaptiveApi::class)
//@Composable
//fun ScaffoldAppContent() {
//    val navigator = rememberListDetailPaneScaffoldNavigator<Long>()
//
//    ListDetailPaneScaffold(
//        directive = navigator.scaffoldDirective,
//        value = navigator.scaffoldValue,
//        listPane = {
//            AnimatedPane {
//                Box(modifier = Modifier.fillMaxWidth().background(Color.Magenta)) {}
//            }
//        },
//        detailPane = {
//            AnimatedPane {
//                Box(modifier = Modifier.fillMaxWidth().background(Color.Green)) {}
//            }
//        },
//    )
//}

