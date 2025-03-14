package es.rlujancreations.database.userPreferences

import androidx.sqlite.SQLiteException
import es.rlujancreations.core.domain.user.UserId
import es.rlujancreations.core.domain.userPreferences.UserPreferencesDataSource
import es.rlujancreations.core.domain.userPreferences.UserPreferencesModel
import es.rlujancreations.core.domain.util.DataError
import es.rlujancreations.core.domain.util.Result
import es.rlujancreations.core.domain.util.EmptyResult
import es.rlujancreations.database.userPreferences.dao.UserPreferencesDAO
import es.rlujancreations.database.userPreferences.mappers.toDomain
import es.rlujancreations.database.userPreferences.mappers.toEntity

/**
 * Created by Raúl L.C. on 14/3/25.
 */
class UserPreferencesDataSourceRoom(
    private val userPreferencesDao: UserPreferencesDAO,
) : UserPreferencesDataSource {
    override suspend fun getUserPreferences(
        userId: UserId,
    ): Result<UserPreferencesModel, DataError.Local> {
        return userPreferencesDao.getUserPreferences(userId)?.let { userPreferences ->
            Result.Success(userPreferences.toDomain())
        } ?: Result.Error(DataError.Local.DATA_NOT_FOUND)
    }

    override suspend fun updateUserPreferences(
        userPreferences: UserPreferencesModel,
    ): EmptyResult<DataError.Local> {
        return try {
            val result = userPreferencesDao.upsertUserPreferences(userPreferences.toEntity())
            Result.Success(result)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }
}
