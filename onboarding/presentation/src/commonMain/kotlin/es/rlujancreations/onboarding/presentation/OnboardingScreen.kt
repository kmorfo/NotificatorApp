package es.rlujancreations.onboarding.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import es.rlujancreations.core.presentation.ObserveAsEvents
import es.rlujancreations.onboarding.presentation.components.OnBoardingPager
import notificatorapp.onboarding.presentation.generated.resources.Res
import notificatorapp.onboarding.presentation.generated.resources.android_firebase_hug
import notificatorapp.onboarding.presentation.generated.resources.cloud_messaging
import notificatorapp.onboarding.presentation.generated.resources.notificator
import notificatorapp.onboarding.presentation.generated.resources.onboarding_subtitle1
import notificatorapp.onboarding.presentation.generated.resources.onboarding_subtitle2
import notificatorapp.onboarding.presentation.generated.resources.onboarding_subtitle3
import notificatorapp.onboarding.presentation.generated.resources.onboarding_title1
import notificatorapp.onboarding.presentation.generated.resources.onboarding_title2
import notificatorapp.onboarding.presentation.generated.resources.onboarding_title3
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
                title = Res.string.onboarding_title1,
                subtitle = Res.string.onboarding_subtitle1,
                image = Res.drawable.notificator,
            ),
            OnboardingPagerInformation(
                title = Res.string.onboarding_title2,
                subtitle = Res.string.onboarding_subtitle2,
                image = Res.drawable.cloud_messaging,
            ),
            OnboardingPagerInformation(
                title = Res.string.onboarding_title3,
                subtitle = Res.string.onboarding_subtitle3,
                image = Res.drawable.android_firebase_hug,
            ),
        )

    OnBoardingPager(
        pages = pages,
        onFinish = { onAction(OnboardingAction.OnFinish) },
        modifier = Modifier.fillMaxSize(),
    )
}
