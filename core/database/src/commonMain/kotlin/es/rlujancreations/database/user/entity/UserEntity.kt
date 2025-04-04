package es.rlujancreations.database.user.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Raúl L.C. on 14/3/25.
 */
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val username: String,
    val email: String,
    val updatedAt: Long,
    val isSynchronized: Boolean,
)
