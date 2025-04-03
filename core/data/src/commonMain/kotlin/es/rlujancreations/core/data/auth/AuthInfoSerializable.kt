package es.rlujancreations.core.data.auth

import kotlinx.serialization.Serializable

/**
 * Created by Raúl L.C. on 3/4/25.
 */
@Serializable
class AuthInfoSerializable(
    val accessToken: String,
    val userId: String,
)
