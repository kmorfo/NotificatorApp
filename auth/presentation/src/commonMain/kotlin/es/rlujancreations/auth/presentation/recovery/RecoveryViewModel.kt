package es.rlujancreations.auth.presentation.recovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rlujancreations.auth.domain.validator.UserDataValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import notificatorapp.auth.presentation.generated.resources.Res
import notificatorapp.auth.presentation.generated.resources.invalid_email

class RecoveryViewModel(
    private val userDataValidator: UserDataValidator,
) : ViewModel() {
    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(RecoveryState())
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
                initialValue = RecoveryState(),
            )

    private val eventChannel = Channel<RecoveryEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: RecoveryAction) {
        when (action) {
            is RecoveryAction.OnEmailChanged -> {
                _state.update {
                    it.copy(
                        email = action.email.trim(),
                        emailError =
                            if (userDataValidator.isValidEmail(email = action.email.trim())) {
                                null
                            } else {
                                Res.string.invalid_email
                            },
                        canRecovery =
                            userDataValidator.isValidEmail(
                                email = action.email.trim(),
                            ),
                    )
                }
            }

            RecoveryAction.OnRecoveryClick -> recovery()
            else -> Unit
        }
    }

    private fun recovery() {
    }
}
