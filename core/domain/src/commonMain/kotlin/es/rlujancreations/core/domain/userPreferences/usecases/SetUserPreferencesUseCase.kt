package es.rlujancreations.core.domain.userPreferences.usecases

import es.rlujancreations.core.domain.userPreferences.UserPreferencesDataSource
import es.rlujancreations.core.domain.userPreferences.UserPreferencesModel
import es.rlujancreations.core.domain.util.DataError
import es.rlujancreations.core.domain.util.EmptyResult

/**
 * Created by Raúl L.C. on 14/3/25.
 */
class SetUserPreferencesUseCase(
    private val userPreferencesDataSource: UserPreferencesDataSource,
) {
    suspend operator fun invoke(
        userPreferences: UserPreferencesModel,
    ): EmptyResult<DataError.Local> {
        return userPreferencesDataSource.updateUserPreferences(userPreferences)
    }
}
