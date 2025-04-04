package es.rlujancreations.auth.data

import kotlinx.serialization.Serializable

/**
 * Created by Raúl L.C. on 3/4/25.
 */
@Serializable
data class AuthResponse(
    val id: String,
    val email: String,
    val username: String,
    val roles: List<String>? = null,
    val token: String? = null,
)
