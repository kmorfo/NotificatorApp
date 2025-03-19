package es.rlujancreations.database.onboarding.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import es.rlujancreations.database.onboarding.entity.OnboardingEntity

/**
 * Created by Raúl L.C. on 18/3/25.
 */
@Dao
interface OnboardingDAO {
    @Query("SELECT * FROM onboarding WHERE id = :id")
    suspend fun getOnboardingById(id: Int): OnboardingEntity?

    @Upsert
    suspend fun upsertOnboarding(onboarding: OnboardingEntity)
}
