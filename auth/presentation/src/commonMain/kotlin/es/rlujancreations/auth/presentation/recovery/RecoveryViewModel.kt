package es.rlujancreations.auth.presentation.recovery

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rlujancreations.auth.domain.usecases.RecoveryUseCase
import es.rlujancreations.auth.domain.validator.UserDataValidator
import es.rlujancreations.core.domain.util.onError
import es.rlujancreations.core.domain.util.onSuccess
import es.rlujancreations.core.presentation.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import notificatorapp.auth.presentation.generated.resources.Res
import notificatorapp.auth.presentation.generated.resources.invalid_email

class RecoveryViewModel(
    private val recoveryUseCase: RecoveryUseCase,
    private val userDataValidator: UserDataValidator,
) : ViewModel() {
    private var hasLoadedInitialData = false
    val snackbarHostState = SnackbarHostState()

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
        _state.update { it.copy(isSendingRecovery = true) }
        viewModelScope.launch {
            recoveryUseCase(email = state.value.email)
                .onSuccess { eventChannel.send(RecoveryEvent.RecoverySentSuccess) }
                .onError { eventChannel.send(RecoveryEvent.Error(it.asUiText())) }
            _state.update { it.copy(isSendingRecovery = false) }
        }
    }
}
