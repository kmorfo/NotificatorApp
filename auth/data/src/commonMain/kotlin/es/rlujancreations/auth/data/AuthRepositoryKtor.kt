package es.rlujancreations.auth.data

import es.rlujancreations.auth.domain.repository.AuthRepository
import es.rlujancreations.core.data.networking.get
import es.rlujancreations.core.data.networking.post
import es.rlujancreations.core.domain.AuthInfo
import es.rlujancreations.core.domain.SessionStorage
import es.rlujancreations.core.domain.user.UserDataSourceLocal
import es.rlujancreations.core.domain.util.DataError
import es.rlujancreations.core.domain.util.EmptyResult
import es.rlujancreations.core.domain.util.Result
import es.rlujancreations.core.domain.util.asEmptyDataResult
import es.rlujancreations.core.domain.util.onSuccess
import io.ktor.client.HttpClient

/**
 * Created by Raúl L.C. on 3/4/25.
 */
class AuthRepositoryKtor(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage,
    private val userDataSourceLocal: UserDataSourceLocal,
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String,
    ): EmptyResult<DataError> {
        val result =
            httpClient.post<AuthRequest, AuthResponse>(
                route = "auth/login",
                body = AuthRequest(email, password),
            )
        return saveResult(result)
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String,
    ): EmptyResult<DataError> {
        val result =
            httpClient.post<RegisterRequest, AuthResponse>(
                route = "auth/register",
                body = RegisterRequest(username, email, password),
            )
        return saveResult(result)
    }

    private suspend fun saveResult(
        result: Result<AuthResponse, DataError.Network>,
    ): EmptyResult<DataError> {
        result
            .onSuccess { userDTO ->
                sessionStorage.set(
                    AuthInfo(
                        accessToken = userDTO.token ?: "",
                        userId = userDTO.id.toString(),
                    ),
                )
                userDataSourceLocal.upsertUser(userDTO.toDomain())
                    .onSuccess { return@onSuccess }
            }

        return result.asEmptyDataResult()
    }

    override suspend fun recovery(email: String): EmptyResult<DataError> {
        val result =
            httpClient.get<Unit>(
                route = "/auth/forgot-password/${email}",
            )

        return result.asEmptyDataResult()
    }
}
