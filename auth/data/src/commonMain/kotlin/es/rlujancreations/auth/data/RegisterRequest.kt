package es.rlujancreations.auth.data

import kotlinx.serialization.Serializable

/**
 * Created by Raúl L.C. on 3/4/25.
 */
@Serializable
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
)
