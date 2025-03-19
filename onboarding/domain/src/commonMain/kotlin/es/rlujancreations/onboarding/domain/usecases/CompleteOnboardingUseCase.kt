package es.rlujancreations.onboarding.domain.usecases

import es.rlujancreations.onboarding.domain.OnboardingRepository

/**
 * Created by Raúl L.C. on 18/3/25.
 */
class CompleteOnboardingUseCase(
    private val repository: OnboardingRepository,
) {
    suspend operator fun invoke() {
        println("Ejecutando CompleteOnboardingUseCase")
//        return repository.completeOnboarding()
    }
}
