package es.rlujancreations.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import es.rlujancreations.database.onboarding.dao.OnboardingDAO
import es.rlujancreations.database.onboarding.entity.OnboardingEntity
import es.rlujancreations.database.user.dao.UserDAO
import es.rlujancreations.database.user.entity.UserEntity
import es.rlujancreations.database.userPreferences.dao.UserPreferencesDAO
import es.rlujancreations.database.userPreferences.entitiy.UserPreferencesEntity

/**
 * Created by Raúl L.C. on 19/1/25.
 */
@Database(
    entities = [
        UserPreferencesEntity::class,
        UserEntity::class,
        OnboardingEntity::class,
    ],
    version = 1,
)
@ConstructedBy(DatabaseConstructor::class)
abstract class NotificatorDatabase : RoomDatabase() {
    abstract val userPreferencesDAO: UserPreferencesDAO
    abstract val userDAO: UserDAO
    abstract val onboardingDAO: OnboardingDAO

    companion object {
        const val DB_NAME = "notificatorApp.db"
    }
}
