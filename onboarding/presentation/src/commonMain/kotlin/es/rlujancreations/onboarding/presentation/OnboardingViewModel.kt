package es.rlujancreations.onboarding.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rlujancreations.onboarding.domain.usecases.CompleteOnboardingUseCase
import es.rlujancreations.onboarding.domain.usecases.HasSeenOnboardingUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val hasSeenOnboardingUseCase: HasSeenOnboardingUseCase,
    private val completeOnboardingUseCase: CompleteOnboardingUseCase,
) : ViewModel() {
    var state by mutableStateOf(OnBoardingState())
        private set

    private val eventChannel = Channel<OnboardingEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            state = state.copy(hasSeenOnboarding = hasSeenOnboardingUseCase())
        }
    }

    fun onAction(action: OnboardingAction) {
        when (action) {
            OnboardingAction.OnFinish -> {
                viewModelScope.launch {
                    completeOnboardingUseCase()
                    state = state.copy(hasSeenOnboarding = true)
                    eventChannel.send(OnboardingEvent.OnFinish)
                }
            }
        }
    }
}
