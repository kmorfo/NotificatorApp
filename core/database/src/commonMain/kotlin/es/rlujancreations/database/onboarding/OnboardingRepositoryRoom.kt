package es.rlujancreations.database.onboarding

import es.rlujancreations.database.onboarding.dao.OnboardingDAO
import es.rlujancreations.database.onboarding.entity.OnboardingEntity
import es.rlujancreations.onboarding.domain.OnboardingRepository

/**
 * Created by Raúl L.C. on 18/3/25.
 */
class OnboardingRepositoryRoom(
    private val onboardingDAO: OnboardingDAO,
) : OnboardingRepository {
    override suspend fun hasSeenOnboarding(): Boolean {
        val local = onboardingDAO.getOnboardingById(1)
        return local?.isComplete ?: false
    }

    override suspend fun completeOnboarding() {
        onboardingDAO.upsertOnboarding(OnboardingEntity(id = 1, isComplete = true))
    }
}
