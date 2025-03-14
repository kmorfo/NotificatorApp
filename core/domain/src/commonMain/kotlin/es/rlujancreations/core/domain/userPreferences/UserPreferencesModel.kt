package es.rlujancreations.core.domain.userPreferences

import es.rlujancreations.core.domain.user.UserId

/**
 * Created by Raúl L.C. on 14/3/25.
 */
data class UserPreferencesModel(
    val userId: UserId,
    val darkMode: DarkModeSettings = DarkModeSettings.SYSTEM_DEFAULT,
    val isSynchronized: Boolean = false,
)
