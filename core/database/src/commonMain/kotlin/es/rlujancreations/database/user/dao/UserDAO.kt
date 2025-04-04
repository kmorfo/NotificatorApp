package es.rlujancreations.database.user.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import es.rlujancreations.database.user.entity.UserEntity

/**
 * Created by Raúl L.C. on 14/3/25.
 */
@Dao
interface UserDAO {
    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getUserById(id: String): UserEntity?

    @Query("SELECT * FROM users WHERE id = :id AND isSynchronized = 0")
    suspend fun getPendingSyncUser(id: String): UserEntity?

    @Upsert
    suspend fun upsertUser(user: UserEntity)
}
