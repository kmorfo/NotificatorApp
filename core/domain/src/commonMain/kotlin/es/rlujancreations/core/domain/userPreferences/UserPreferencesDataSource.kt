package es.rlujancreations.core.domain.userPreferences

import es.rlujancreations.core.domain.user.UserId
import es.rlujancreations.core.domain.util.DataError
import es.rlujancreations.core.domain.util.EmptyResult
import es.rlujancreations.core.domain.util.Result

/**
 * Created by Raúl L.C. on 14/3/25.
 */
interface UserPreferencesDataSource {
    suspend fun getUserPreferences(userId: UserId): Result<UserPreferencesModel, DataError.Local>

    suspend fun updateUserPreferences(
        userPreferences: UserPreferencesModel,
    ): EmptyResult<DataError.Local>
}
