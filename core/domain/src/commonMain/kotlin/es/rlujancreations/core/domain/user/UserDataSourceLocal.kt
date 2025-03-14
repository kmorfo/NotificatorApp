package es.rlujancreations.core.domain.user

import es.rlujancreations.core.domain.util.DataError
import es.rlujancreations.core.domain.util.Result

/**
 * Created by Raúl L.C. on 14/3/25.
 */

interface UserDataSourceLocal {
    suspend fun getPendingSyncUser(userId: UserId): Result<UserModel, DataError.Local>

    suspend fun getUserById(userId: UserId): Result<UserModel, DataError.Local>

    suspend fun upsertUser(
        user: UserModel,
        isSynchronized: Boolean = true,
    ): Result<UserId, DataError.Local>
}
