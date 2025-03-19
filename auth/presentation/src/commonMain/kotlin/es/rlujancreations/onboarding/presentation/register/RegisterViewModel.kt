package es.rlujancreations.onboarding.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class RegisterViewModel : ViewModel() {
    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(RegisterState())
    val state =
        _state
            .onStart {
                if (!hasLoadedInitialData) {
                    /** Load initial data here **/
                    hasLoadedInitialData = true
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = RegisterState(),
            )

    fun onAction(action: RegisterAction) {
        when (action) {
            else -> TODO("Handle actions")
        }
    }
}
