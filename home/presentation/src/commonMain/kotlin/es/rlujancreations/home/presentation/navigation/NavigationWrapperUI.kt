package es.rlujancreations.home.presentation.navigation
//
// import androidx.compose.material3.Text
// import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
// import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
// import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
// import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
// import androidx.compose.runtime.Composable
// import androidx.compose.runtime.getValue
// import androidx.compose.runtime.mutableStateOf
// import androidx.compose.runtime.remember
// import androidx.compose.runtime.setValue
// import androidx.window.core.layout.WindowWidthSizeClass
// import es.rlujancreations.core.presentation.IconDisplay
// import org.jetbrains.compose.resources.stringResource

/**
 * Created by Raúl L.C. on 12/3/25.
 */

// @Composable
// fun NavigationWrapperUI(content: @Composable () -> Unit = {}) {
//    var selectedDestination: NotificatorDestinations by remember {
//        mutableStateOf(NotificatorDestinations.Projects)
//    }
//    val adaptiveInfo = currentWindowAdaptiveInfo()
//    val windowSize = adaptiveInfo.windowSizeClass.windowWidthSizeClass
//
//    val navLayoutType =
//        if (windowSize == WindowWidthSizeClass.EXPANDED) {
//            NavigationSuiteType.NavigationDrawer
//        } else {
//            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(currentWindowAdaptiveInfo())
//        }
//
//    NavigationSuiteScaffold(
//        navigationSuiteItems = {
//            NotificatorDestinations.entries.forEach {
//                item(
//                    label = { Text(stringResource(it.title)) },
//                    icon = {
//                        IconDisplay(
//                            appIcon = it.icon(),
//                            contentDescription = stringResource(it.title),
//                        )
//                    },
//                    selected = it == selectedDestination,
//                    onClick = {
//                        selectedDestination = it
//                    },
//                )
//            }
//        },
//        layoutType = navLayoutType,
//    ) {
//        content()
//    }
// }
