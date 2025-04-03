package es.rlujancreations.core.data.auth

import es.rlujancreations.core.domain.AuthInfo

/**
 * Created by Raúl L.C. on 3/4/25.
 */
fun AuthInfo.toAuthInfoSerializable(): AuthInfoSerializable {
    return AuthInfoSerializable(
        accessToken = accessToken,
        userId = userId,
    )
}

fun AuthInfoSerializable.toAuthInfo(): AuthInfo {
    return AuthInfo(
        accessToken = accessToken,
        userId = userId,
    )
}
