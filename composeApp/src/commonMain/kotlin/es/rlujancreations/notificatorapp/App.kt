package es.rlujancreations.notificatorapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import es.rlujancreations.core.domain.IKSecureStorage
import es.rlujancreations.core.presentation.theme.NotificatorAppTheme
import es.rlujancreations.notificatorapp.navigation.NavigationWrapperUI
import org.koin.compose.KoinContext


@Composable
fun App() {
//    val adaptiveInfo = currentWindowAdaptiveInfo()
//    val sizeClassText =
//        "${adaptiveInfo.windowSizeClass.windowWidthSizeClass}\n" +
//            "${adaptiveInfo.windowSizeClass.windowHeightSizeClass}"

    KoinContext {
        NotificatorAppTheme {
//            val navController = rememberNavController()
//
//            NavigationRoot(
//                navController = navController,
//            )
//            Text(
//                text = sizeClassText,
//                color = Color.Magenta,
//                modifier = Modifier.padding(WindowInsets.safeDrawing.asPaddingValues())
//            )
            NavigationWrapperUI {
                ScaffoldAppContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ScaffoldAppContent() {
    val navigator = rememberListDetailPaneScaffoldNavigator<Long>()

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            AnimatedPane {
                Box(modifier = Modifier.fillMaxWidth().background(Color.Magenta)) {}
            }
        },
        detailPane = {
            AnimatedPane {
                Box(modifier = Modifier.fillMaxWidth().background(Color.Green)) {}
            }
        },
    )
}
