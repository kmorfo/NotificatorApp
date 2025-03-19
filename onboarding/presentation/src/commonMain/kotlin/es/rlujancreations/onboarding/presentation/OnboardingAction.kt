package es.rlujancreations.onboarding.presentation

sealed interface OnboardingAction {
    data object OnFinish : OnboardingAction
}
