package es.rlujancreations.database.user.mappers

import es.rlujancreations.core.domain.user.UserModel
import es.rlujancreations.database.user.entity.UserEntity
import kotlinx.datetime.Clock

/**
 * Created by Raúl L.C. on 14/3/25.
 */
fun UserEntity.toDomain() =
    UserModel(
        id = id,
        username = username,
        email = email,
    )

fun UserModel.toUserEntity(isSynchronized: Boolean = true): UserEntity {
    return UserEntity(
        id = id ?: "",
        username = username,
        email = email,
        updatedAt = Clock.System.now().epochSeconds,
        isSynchronized = isSynchronized,
    )
}
