package es.rlujancreations.auth.domain.usecases

import es.rlujancreations.auth.domain.repository.AuthRepository
import es.rlujancreations.core.domain.util.DataError
import es.rlujancreations.core.domain.util.EmptyResult

/**
 * Created by Raúl L.C. on 3/4/25.
 */
class RegisterUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(
        username: String,
        email: String,
        password: String,
    ): EmptyResult<DataError> {
        return repository.register(username, email, password)
    }
}
