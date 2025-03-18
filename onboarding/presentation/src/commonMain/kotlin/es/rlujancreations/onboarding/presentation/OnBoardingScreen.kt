package es.rlujancreations.onboarding.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnBoardingRoot(
    viewModel: OnBoardingViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    OnBoardingScreen(
        state = state,
        onAction = viewModel::onAction,
    )
}

@Composable
fun OnBoardingScreen(
    state: OnBoardingState,
    onAction: (OnBoardingAction) -> Unit,
) {
    Text("OnBoarding Screen")
}
