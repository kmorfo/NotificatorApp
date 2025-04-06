package es.rlujancreations.notificatorapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import es.rlujancreations.core.presentation.theme.NotificatorAppTheme
import es.rlujancreations.notificatorapp.navigation.NavigationRoot
import es.rlujancreations.notificatorapp.navigation.Screen
import notificatorapp.composeapp.generated.resources.Res
import notificatorapp.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    KoinContext {
        NotificatorAppTheme {
            val viewModel = koinViewModel<MainViewModel>()
            val navController = rememberNavController()

            if (viewModel.state.isLoading) {
                AnimatedVisibility(
                    visible = viewModel.state.isLoading,
                    enter = scaleIn(),
                ) {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.logo),
                            contentDescription = "App Logo",
                            modifier =
                                Modifier
                                    .aspectRatio(1f)
                                    .padding(25.dp)
                                    .shadow(
                                        elevation = 25.dp,
                                        shape = CircleShape,
                                        spotColor = Color(0xff4285f4),
                                        ambientColor = Color(0xff4285f4),
                                    )
                                    .clip(CircleShape),
                        )
                    }
                }
            } else {
                NavigationRoot(
                    navController = navController,
                    startDestination = getStartDestination(viewModel.state),
                )
            }
        }
    }
}

private fun getStartDestination(state: MainState): Any {
    return when {
        state.isLoggedIn -> Screen.Home
        state.hasSeenOnboarding -> Screen.Auth
        else -> Screen.Onboarding
    }
}
