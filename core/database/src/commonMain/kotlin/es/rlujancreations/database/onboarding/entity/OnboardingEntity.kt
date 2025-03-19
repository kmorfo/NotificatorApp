package es.rlujancreations.database.onboarding.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Raúl L.C. on 18/3/25.
 */
@Entity(tableName = "onboarding")
data class OnboardingEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val isComplete: Boolean,
)
