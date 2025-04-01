package es.rlujancreations.notificatorapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import es.rlujancreations.home.presentation.HomeScreenRoot
import es.rlujancreations.onboarding.presentation.OnBoardingRoot
import es.rlujancreations.auth.presentation.login.LoginScreenRoot
import es.rlujancreations.auth.presentation.recovery.RecoveryScreenRoot
import es.rlujancreations.auth.presentation.register.RegisterScreenRoot

/**
 * Created by Raúl L.C. on 19/1/25.
 */
@Composable
fun NavigationRoot(
    navController: NavHostController = rememberNavController(),
    startDestination: Any = Screen.Onboarding,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        onboardingGraph(navController)
        authGraph(navController)
        homeGraph(navController)
    }
}

private fun NavGraphBuilder.onboardingGraph(navController: NavHostController) {
    navigation<Screen.Onboarding>(startDestination = Screen.Onboarding.Intro) {
        composable<Screen.Onboarding.Intro> {
            OnBoardingRoot(
                onFinish = { navController.navigate(Screen.Auth.Login) },
            )
        }
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<Screen.Auth>(startDestination = Screen.Auth.Login) {
        composable<Screen.Auth.Login> {
            LoginScreenRoot(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.Scaffold) {
                        popUpTo(Screen.Auth) {
                            inclusive = true
                        }
                    }
                },
                onSignUpClick = { navController.navigate(Screen.Auth.Register) },
                onRecoveryClick = { navController.navigate(Screen.Auth.Recovery) },
            )
        }
        composable<Screen.Auth.Register> {
            RegisterScreenRoot()
        }
        composable<Screen.Auth.Recovery> {
            RecoveryScreenRoot(
                onLoginClick = {
                    navController.navigate(Screen.Auth.Login) {
                        popUpTo(Screen.Auth.Recovery) {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
            )
        }
    }
}

private fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation<Screen.Home>(startDestination = Screen.Home.Scaffold) {
        composable<Screen.Home.Scaffold> {
            HomeScreenRoot(
                onSettingsClick = { navController.navigate(Screen.Home.Settings) },
                onTokenExpired = {
                    navController.navigate(Screen.Auth.Login) {
                        popUpTo(Screen.Home.Scaffold) { inclusive = true }
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}
