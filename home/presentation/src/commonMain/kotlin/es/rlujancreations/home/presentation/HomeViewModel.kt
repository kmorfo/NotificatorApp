package es.rlujancreations.home.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Created by Raúl L.C. on 17/3/25.
 */
class HomeViewModel() : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    val snackbarHostState = SnackbarHostState()

    private val eventChannel = Channel<HomeEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        getUserData()
    }

    private fun getUserData() {
    }

    fun onAction(action: HomeAction) {
        when (action) {
            else -> {}
        }
    }
}
