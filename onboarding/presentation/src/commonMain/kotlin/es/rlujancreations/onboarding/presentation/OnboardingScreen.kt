package es.rlujancreations.onboarding.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.rlujancreations.core.presentation.ObserveAsEvents
import es.rlujancreations.onboarding.presentation.components.OnBoardingPager
import notificatorapp.onboarding.presentation.generated.resources.Res
import notificatorapp.onboarding.presentation.generated.resources.cloud_messaging
import notificatorapp.onboarding.presentation.generated.resources.notificator
import notificatorapp.onboarding.presentation.generated.resources.onboard_app
import notificatorapp.onboarding.presentation.generated.resources.onboard_device
import notificatorapp.onboarding.presentation.generated.resources.onboard_message
import notificatorapp.onboarding.presentation.generated.resources.onboard_project
import notificatorapp.onboarding.presentation.generated.resources.onboarding_subtitle_app
import notificatorapp.onboarding.presentation.generated.resources.onboarding_subtitle_device
import notificatorapp.onboarding.presentation.generated.resources.onboarding_subtitle_message
import notificatorapp.onboarding.presentation.generated.resources.onboarding_subtitle_notificator
import notificatorapp.onboarding.presentation.generated.resources.onboarding_subtitle_project
import notificatorapp.onboarding.presentation.generated.resources.onboarding_title_app
import notificatorapp.onboarding.presentation.generated.resources.onboarding_title_device
import notificatorapp.onboarding.presentation.generated.resources.onboarding_title_message
import notificatorapp.onboarding.presentation.generated.resources.onboarding_title_notificator
import notificatorapp.onboarding.presentation.generated.resources.onboarding_title_project
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnBoardingRoot(
    onFinish: () -> Unit,
    viewModel: OnboardingViewModel = koinViewModel(),
) {
    ObserveAsEvents(viewModel.events) { events ->
        when (events) {
            OnboardingEvent.OnFinish -> onFinish()
        }
    }

    OnBoardingScreen(
        state = viewModel.state,
        onAction = viewModel::onAction,
    )
}

@Composable
fun OnBoardingScreen(
    state: OnBoardingState,
    onAction: (OnboardingAction) -> Unit,
) {
    val pages: List<OnboardingPagerInformation> =
        listOf(
            OnboardingPagerInformation(
                title = Res.string.onboarding_title_notificator,
                subtitle = Res.string.onboarding_subtitle_notificator,
                image = Res.drawable.notificator,
            ),
            OnboardingPagerInformation(
                title = Res.string.onboarding_title_project,
                subtitle = Res.string.onboarding_subtitle_project,
                image = Res.drawable.onboard_project,
            ),
            OnboardingPagerInformation(
                title = Res.string.onboarding_title_app,
                subtitle = Res.string.onboarding_subtitle_app,
                image = Res.drawable.onboard_app,
            ),
            OnboardingPagerInformation(
                title = Res.string.onboarding_title_device,
                subtitle = Res.string.onboarding_subtitle_device,
                image = Res.drawable.onboard_device,
            ),
            OnboardingPagerInformation(
                title = Res.string.onboarding_title_message,
                subtitle = Res.string.onboarding_subtitle_message,
                image = Res.drawable.onboard_message,
            ),
        )
    Scaffold { paddingValues ->
        OnBoardingPager(
            pages = pages,
            onFinish = { onAction(OnboardingAction.OnFinish) },
            modifier = Modifier.fillMaxSize().padding(paddingValues),
        )
    }
}
