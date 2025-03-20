package es.rlujancreations.notificatorapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rlujancreations.onboarding.domain.usecases.HasSeenOnboardingUseCase
import kotlinx.coroutines.launch

/**
 * Created by Raúl L.C. on 20/3/25.
 */
class MainViewModel(
    hasSeenOnboardingUseCase: HasSeenOnboardingUseCase,
) : ViewModel() {
    var state by mutableStateOf(MainState())
        private set

    init {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            state = state.copy(hasSeenOnboarding = hasSeenOnboardingUseCase())
            state = state.copy(isLoading = false)
        }
    }
}
