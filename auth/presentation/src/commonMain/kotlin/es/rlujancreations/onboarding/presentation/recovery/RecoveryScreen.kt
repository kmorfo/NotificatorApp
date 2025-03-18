package es.rlujancreations.onboarding.presentation.recovery

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RecoveryScreenRoot(
    viewModel: RecoveryViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RecoveryScreen(
        state = state,
        onAction = viewModel::onAction,
    )
}

@Composable
fun RecoveryScreen(
    state: RecoveryState,
    onAction: (RecoveryAction) -> Unit,
) {
    Text("Recovery Screen")
}
