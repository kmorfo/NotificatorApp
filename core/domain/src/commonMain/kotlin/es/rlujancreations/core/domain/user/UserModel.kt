package es.rlujancreations.core.domain.user

/**
 * Created by Raúl L.C. on 14/3/25.
 */

typealias UserId = Int

data class UserModel(
    var id: Int? = null,
    val username: String,
    val email: String,
)
