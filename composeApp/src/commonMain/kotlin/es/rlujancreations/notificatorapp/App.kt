package es.rlujancreations.notificatorapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import es.rlujancreations.core.presentation.theme.NotificatorAppTheme
import es.rlujancreations.notificatorapp.navigation.NavigationRoot
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        NotificatorAppTheme {
            val navController = rememberNavController()

            NavigationRoot(
                navController = navController,
            )
        }
    }
}

