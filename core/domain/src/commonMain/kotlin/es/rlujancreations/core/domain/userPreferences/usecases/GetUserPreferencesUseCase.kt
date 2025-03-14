package es.rlujancreations.core.domain.userPreferences.usecases

import es.rlujancreations.core.domain.user.UserId
import es.rlujancreations.core.domain.userPreferences.UserPreferencesDataSource
import es.rlujancreations.core.domain.userPreferences.UserPreferencesModel
import es.rlujancreations.core.domain.util.DataError
import es.rlujancreations.core.domain.util.Result

/**
 * Created by Raúl L.C. on 14/3/25.
 */
class GetUserPreferencesUseCase(
    private val userPreferencesDataSource: UserPreferencesDataSource,
) {
    suspend operator fun invoke(userId: UserId): Result<UserPreferencesModel, DataError.Local> {
        return userPreferencesDataSource.getUserPreferences(userId)
    }
}
