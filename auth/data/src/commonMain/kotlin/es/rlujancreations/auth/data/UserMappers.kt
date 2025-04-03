package es.rlujancreations.auth.data

import es.rlujancreations.core.domain.user.UserModel

/**
 * Created by Raúl L.C. on 3/4/25.
 */
fun AuthResponse.toDomain(): UserModel {
    return UserModel(
        id = id,
        email = email,
        username = username,
    )
}
