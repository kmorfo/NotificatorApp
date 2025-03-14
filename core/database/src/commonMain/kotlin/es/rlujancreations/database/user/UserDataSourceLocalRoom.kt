package es.rlujancreations.database.user

import androidx.sqlite.SQLiteException
import es.rlujancreations.core.domain.user.UserDataSourceLocal
import es.rlujancreations.core.domain.user.UserId
import es.rlujancreations.core.domain.user.UserModel
import es.rlujancreations.core.domain.util.DataError
import es.rlujancreations.core.domain.util.Result
import es.rlujancreations.database.user.dao.UserDAO
import es.rlujancreations.database.user.mappers.toDomain
import es.rlujancreations.database.user.mappers.toUserEntity

/**
 * Created by Raúl L.C. on 14/3/25.
 */
class UserDataSourceLocalRoom(
    private val userDao: UserDAO,
) : UserDataSourceLocal {
    override suspend fun getPendingSyncUser(userId: UserId): Result<UserModel, DataError.Local> {
        return userDao.getPendingSyncUser(userId)?.let { user ->
            Result.Success(user.toDomain())
        } ?: Result.Error(DataError.Local.DATA_NOT_FOUND)
    }

    override suspend fun getUserById(userId: UserId): Result<UserModel, DataError.Local> {
        return userDao.getUserById(userId)?.let { user ->
            Result.Success(user.toDomain())
        } ?: Result.Error(DataError.Local.DATA_NOT_FOUND)
    }

    override suspend fun upsertUser(
        user: UserModel,
        isSynchronized: Boolean,
    ): Result<UserId, DataError.Local> {
        return try {
            val entity = user.toUserEntity()
            userDao.upsertUser(user = user.toUserEntity(isSynchronized = isSynchronized))
            Result.Success(entity.id)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }
}
