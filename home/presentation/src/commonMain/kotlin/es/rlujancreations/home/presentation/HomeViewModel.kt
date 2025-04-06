package es.rlujancreations.home.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rlujancreations.core.domain.user.UserDataSourceLocal
import es.rlujancreations.core.domain.user.usecases.GetLoggedUserIdUseCase
import es.rlujancreations.core.domain.user.usecases.LogoutUseCase
import es.rlujancreations.core.domain.util.onError
import es.rlujancreations.core.domain.util.onSuccess
import es.rlujancreations.core.presentation.UiText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import notificatorapp.home.presentation.generated.resources.Res
import notificatorapp.home.presentation.generated.resources.invalid_token

/**
 * Created by Raúl L.C. on 17/3/25.
 */
class HomeViewModel(
    private val getLoggedUserIdUseCase: GetLoggedUserIdUseCase,
    private val userDataSourceLocal: UserDataSourceLocal,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    val snackbarHostState = SnackbarHostState()

    private val eventChannel = Channel<HomeEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        getUserData()
    }

    private fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            val authInfo = getLoggedUserIdUseCase()
            if (authInfo != null) {
                userDataSourceLocal.getUserById(authInfo.userId)
                    .onSuccess { userRoom ->
                        state = state.copy(userId = authInfo.userId, user = userRoom)
                    }
                    .onError {
                        invalidToken()
                    }
            }
        }
    }

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnItemClicked -> {
                state = state.copy(selectedIndex = action.index)
            }

            HomeAction.OnSettingsClick -> {
            }

            HomeAction.OnTokenExpired -> {
            }

            else -> Unit
        }
    }

    private suspend fun invalidToken() {
        eventChannel.send(
            HomeEvent.ErrorTokenExpired(UiText.StringResource(Res.string.invalid_token)),
        )

        viewModelScope.launch {
            delay(1500L)
            logoutUseCase()
        }
    }
}
