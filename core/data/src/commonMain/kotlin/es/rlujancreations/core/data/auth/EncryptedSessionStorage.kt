package es.rlujancreations.core.data.auth

import es.rlujancreations.core.domain.AuthInfo
import es.rlujancreations.core.domain.IKSecureStorage
import es.rlujancreations.core.domain.SessionStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Created by Raúl L.C. on 3/4/25.
 */
class EncryptedSessionStorage(
    private val kSecureStorage: IKSecureStorage,
) : SessionStorage {
    companion object {
        private const val AUTH_INFO_KEY = "AUTH_INFO_KEY"
    }

    override suspend fun get(): AuthInfo? {
        return withContext(Dispatchers.IO) {
            val json = kSecureStorage.getItem(AUTH_INFO_KEY)
            json?.let {
                Json.decodeFromString<AuthInfoSerializable>(it).toAuthInfo()
            }
        }
    }

    override suspend fun set(authInfo: AuthInfo) {
        return withContext(Dispatchers.IO) {
            if (authInfo == null) {
                kSecureStorage.removeItem(AUTH_INFO_KEY)
                return@withContext
            }
            val json = Json.encodeToString(authInfo.toAuthInfoSerializable())
            kSecureStorage.setItem(AUTH_INFO_KEY, json)
        }
    }
}
