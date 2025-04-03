package es.rlujancreations.auth.domain.repository

import es.rlujancreations.core.domain.util.DataError
import es.rlujancreations.core.domain.util.EmptyResult

/**
 * Created by Raúl L.C. on 3/4/25.
 */
interface AuthRepository {
    suspend fun login(
        email: String,
        password: String,
    ): EmptyResult<DataError>

    suspend fun register(
        username: String,
        email: String,
        password: String,
    ): EmptyResult<DataError>

    suspend fun recovery(email: String): EmptyResult<DataError>
}
