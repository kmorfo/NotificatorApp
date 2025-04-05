package es.rlujancreations.core.domain.user.usecases

import es.rlujancreations.core.domain.SessionStorage

/**
 * Created by Raúl L.C. on 5/4/25.
 */
class LogoutUseCase(
    private val sessionStorage: SessionStorage,
) {
    suspend operator fun invoke() {
        sessionStorage.set(authInfo = null)
    }
}
