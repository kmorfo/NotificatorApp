package es.rlujancreations.database.userPreferences.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.rlujancreations.core.domain.user.UserId
import es.rlujancreations.core.domain.userPreferences.DarkModeSettings

/**
 * Created by Raúl L.C. on 19/1/25.
 */
@Entity(tableName = "user_preferences")
data class UserPreferencesEntity(
    @PrimaryKey(autoGenerate = false)
    val userId: UserId,
    val darkMode: DarkModeSettings = DarkModeSettings.SYSTEM_DEFAULT,
    val isSynchronized: Boolean = false,
)
