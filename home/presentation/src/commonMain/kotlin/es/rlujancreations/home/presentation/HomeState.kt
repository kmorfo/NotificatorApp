package es.rlujancreations.home.presentation

import es.rlujancreations.core.domain.user.UserModel

/**
 * Created by Raúl L.C. on 17/3/25.
 */
data class HomeState(
    val isLoading: Boolean = false,
    val userId: String = "-1",
    val user: UserModel? = null,
)
