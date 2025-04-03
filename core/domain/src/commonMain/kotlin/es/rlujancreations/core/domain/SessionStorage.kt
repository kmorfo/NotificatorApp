package es.rlujancreations.core.domain

/**
 * Created by Raúl L.C. on 3/4/25.
 */
interface SessionStorage {
    suspend fun get(): AuthInfo?

    suspend fun set(authInfo: AuthInfo)
}
