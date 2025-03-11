package es.rlujancreations.notificatorapp

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import es.rlujancreations.notificatorapp.navigation.NavigationRoot
import org.koin.compose.KoinContext

@Composable
fun App() {
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val sizeClassText =
        "${adaptiveInfo.windowSizeClass.windowWidthSizeClass}\n" +
            "${adaptiveInfo.windowSizeClass.windowHeightSizeClass}"

    KoinContext {
        MaterialTheme {
//            val navController = rememberNavController()
//
//            NavigationRoot(
//                navController = navController,
//            )
            Text(
                text = sizeClassText,
                color = Color.Magenta,
                modifier = Modifier.padding(WindowInsets.safeDrawing.asPaddingValues())
            )
        }
    }
}
