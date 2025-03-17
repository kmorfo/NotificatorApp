package es.rlujancreations.notificatorapp.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import es.rlujancreations.home.presentation.HomeScreenRoot

/**
 * Created by Raúl L.C. on 19/1/25.
 */
@Composable
fun NavigationRoot(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home,
    ) {
        authGraph(navController)
        homeGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<Screen.Auth>(startDestination = Screen.Auth.Intro) {
        composable<Screen.Auth.Intro> {
            Text("Intro")
        }
    }
}

private fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation<Screen.Home>(startDestination = Screen.Home.Scaffold) {
        composable<Screen.Home.Scaffold> {
            HomeScreenRoot(
                onSettingsClick = { navController.navigate(Screen.Home.Settings) },
                onTokenExpired = {
                    navController.navigate(Screen.Auth.Intro) {
                        popUpTo(Screen.Home.Scaffold) { inclusive = true }
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}
