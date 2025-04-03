package es.rlujancreations.auth.data

import kotlinx.serialization.Serializable

/**
 * Created by Raúl L.C. on 3/4/25.
 */
@Serializable
data class AuthRequest(
    val email: String,
    val password: String,
)
