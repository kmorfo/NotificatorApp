package es.rlujancreations.onboarding.domain

/**
 * Created by Raúl L.C. on 18/3/25.
 */
interface OnboardingRepository {
    suspend fun hasSeenOnboarding(): Boolean

    suspend fun completeOnboarding()
}
