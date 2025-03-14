package es.rlujancreations.database.userPreferences.mappers

import es.rlujancreations.core.domain.userPreferences.UserPreferencesModel
import es.rlujancreations.database.userPreferences.entitiy.UserPreferencesEntity

/**
 * Created by Raúl L.C. on 14/3/25.
 */
fun UserPreferencesEntity.toDomain() =
    UserPreferencesModel(
        userId = userId,
        darkMode = darkMode,
        isSynchronized = isSynchronized,
    )

fun UserPreferencesModel.toEntity() =
    UserPreferencesEntity(
        userId = userId,
        darkMode = darkMode,
        isSynchronized = isSynchronized,
    )
