package es.rlujancreations.database.userPreferences.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import es.rlujancreations.core.domain.user.UserId
import es.rlujancreations.database.userPreferences.entitiy.UserPreferencesEntity

/**
 * Created by Raúl L.C. on 19/1/25.
 */
@Dao
interface UserPreferencesDAO {
    @Query("SELECT * FROM user_preferences WHERE userId = :userId")
    suspend fun getUserPreferences(userId: UserId): UserPreferencesEntity?

    @Upsert
    suspend fun upsertUserPreferences(userPreferences: UserPreferencesEntity)
}
