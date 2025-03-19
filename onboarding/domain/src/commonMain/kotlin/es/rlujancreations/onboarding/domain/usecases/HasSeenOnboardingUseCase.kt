package es.rlujancreations.onboarding.domain.usecases

import es.rlujancreations.onboarding.domain.OnboardingRepository

/**
 * Created by Raúl L.C. on 18/3/25.
 */
class HasSeenOnboardingUseCase(
    private val repository: OnboardingRepository,
) {
    suspend operator fun invoke(): Boolean {
        println("Ejecutando HasSeenOnboardingUseCase")
        return repository.hasSeenOnboarding()
    }
}
