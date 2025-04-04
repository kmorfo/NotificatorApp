package es.rlujancreations.core.domain.user

/**
 * Created by Raúl L.C. on 14/3/25.
 */

typealias UserId = String

data class UserModel(
    var id: String? = null,
    val username: String,
    val email: String,
)
