package es.rlujancreations.onboarding.presentation

/**
 * Created by Raúl L.C. on 19/3/25.
 */
sealed interface OnboardingEvent {
    data object OnFinish : OnboardingEvent
}
