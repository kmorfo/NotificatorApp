package es.rlujancreations.auth.presentation.recovery

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rlujancreations.core.presentation.EmailIcon
import es.rlujancreations.core.presentation.IconDisplay
import es.rlujancreations.core.presentation.LogoIcon
import es.rlujancreations.core.presentation.LogoRLujanIcon
import es.rlujancreations.core.presentation.ObserveAsEvents
import es.rlujancreations.core.presentation.Shapes
import es.rlujancreations.core.presentation.StringProvider
import es.rlujancreations.core.presentation.WindowWidthSizeClass
import es.rlujancreations.core.presentation.components.MySnackbar
import es.rlujancreations.core.presentation.components.NotificatorActionButton
import es.rlujancreations.core.presentation.components.NotificatorTextField
import es.rlujancreations.core.presentation.extensions.rotateVertically
import es.rlujancreations.core.presentation.getScreenDimensions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import notificatorapp.auth.presentation.generated.resources.Res
import notificatorapp.auth.presentation.generated.resources.already_have_an_account
import notificatorapp.auth.presentation.generated.resources.btn_login
import notificatorapp.auth.presentation.generated.resources.btn_recovery
import notificatorapp.auth.presentation.generated.resources.cd_email
import notificatorapp.auth.presentation.generated.resources.cd_logo_rlc
import notificatorapp.auth.presentation.generated.resources.email
import notificatorapp.auth.presentation.generated.resources.example_email
import notificatorapp.auth.presentation.generated.resources.recovery_account
import notificatorapp.auth.presentation.generated.resources.recovery_explanation
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RecoveryScreenRoot(
    onLoginClick: () -> Unit,
    viewModel: RecoveryViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RecoveryScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is RecoveryAction.OnLoginClick -> onLoginClick()
                else -> Unit
            }
            viewModel.onAction(action)
        },
        events = viewModel.events,
        snackbarHostState = viewModel.snackbarHostState,
    )
}

@Composable
fun RecoveryScreen(
    state: RecoveryState,
    onAction: (RecoveryAction) -> Unit,
    events: Flow<RecoveryEvent>,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
) {
    val windowWidthSizeClass = getScreenDimensions().windowWidthSizeClass
    val scope = rememberCoroutineScope()
    val stringProvider: StringProvider = koinInject()

    ObserveAsEvents(events) { event ->
        when (event) {
            is RecoveryEvent.Error -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = event.error.asString(stringProvider),
                        duration = SnackbarDuration.Short,
                    )
                }
            }

            RecoveryEvent.RecoverySentSuccess -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Recovery OK",
                        duration = SnackbarDuration.Short,
                    )
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { snackbarData ->
                MySnackbar(snackbarData)
            }
        },
    ) { paddingValues ->
        when (windowWidthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Column(
                        modifier = Modifier.weight(0.25f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        IconDisplay(LogoIcon(), modifier = Modifier.size(180.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Notificator",
                                fontSize = 32.sp,
                                color = MaterialTheme.colorScheme.background,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Text(
                                text = "APP",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.background,
                                modifier = Modifier.rotateVertically(),
                            )
                        }
                    }
                    RecoveryForm(
                        state = state,
                        onAction = onAction,
                    )
                    Spacer(modifier = Modifier.weight(0.04f))
                }
            }

            WindowWidthSizeClass.Medium -> {
                Column(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .background(MaterialTheme.colorScheme.background)
                            .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Column(
                        modifier = Modifier.weight(0.25f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        IconDisplay(LogoIcon(), modifier = Modifier.size(150.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Notificator",
                                fontSize = 38.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Text(
                                text = "APP",
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.rotateVertically(),
                            )
                        }
                    }
                    RecoveryForm(
                        state = state,
                        onAction = onAction,
                        modifier = Modifier.weight(0.5f),
                    )
                    Spacer(modifier = Modifier.weight(0.06f))
                }
            }

            WindowWidthSizeClass.Expanded -> {
                Row(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .background(MaterialTheme.colorScheme.onBackground),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background)
                                .weight(0.5f),
                    ) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceAround,
                            ) {
                                IconDisplay(LogoIcon(), modifier = Modifier.size(250.dp))
                                Text(
                                    text = "Notificator",
                                    fontSize = 65.sp,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            }

                            Text(
                                text = "APP",
                                fontSize = 38.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.rotateVertically(),
                            )
                        }
                    }
                    RecoveryForm(
                        state = state,
                        onAction = onAction,
                        modifier = Modifier.weight(0.5f),
                    )
                }
            }
        }
    }
}

@Composable
private fun RecoveryForm(
    state: RecoveryState,
    onAction: (RecoveryAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val windowWidthSizeClass = getScreenDimensions().windowWidthSizeClass

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier =
            modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .background(
                    MaterialTheme.colorScheme.background,
                    shape = Shapes.medium,
                )
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = Shapes.medium,
                ),
    ) {
        if (windowWidthSizeClass != WindowWidthSizeClass.Compact) {
            IconDisplay(
                LogoRLujanIcon(),
                contentDescription = stringResource(Res.string.cd_logo_rlc),
                modifier =
                    Modifier
                        .padding(top = 32.dp)
                        .widthIn(min = 120.dp, max = 250.dp),
            )
        } else {
            Spacer(modifier = Modifier.height(24.dp))
        }
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
        ) {
            Text(
                text = stringResource(Res.string.recovery_account),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(4.dp))
            val annotatedLoginString =
                buildAnnotatedString {
                    withStyle(
                        style =
                            SpanStyle(
                                fontWeight = FontWeight.Thin,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                            ),
                    ) {
                        append(stringResource(Res.string.already_have_an_account) + " ")
                        pushStringAnnotation(
                            tag = "clickable_login_text",
                            annotation = stringResource(Res.string.btn_login),
                        )
                        withStyle(
                            style =
                                SpanStyle(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                ),
                        ) {
                            append(stringResource(Res.string.btn_login))
                        }
                    }
                }
            Text(
                text = annotatedLoginString,
                modifier =
                    Modifier.clickable {
                        onAction(RecoveryAction.OnLoginClick)
                    },
            )
            Spacer(modifier = Modifier.height(16.dp))
            NotificatorTextField(
                value = state.email,
                onValueChange = { onAction(RecoveryAction.OnEmailChanged(it)) },
                placeholder = stringResource(Res.string.example_email),
                contentDescription = stringResource(Res.string.cd_email),
                title = stringResource(Res.string.email),
                leadingIcon = EmailIcon(),
                trailingIcon = null,
                error = state.emailError?.let { stringResource(it) },
                maxLines = 1,
                minLines = 1,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(Res.string.recovery_explanation),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(16.dp))
            NotificatorActionButton(
                text = stringResource(Res.string.btn_recovery),
                isLoading = state.isSendingRecovery,
                enabled = state.emailError == null && state.email.isNotBlank() && !state.isSendingRecovery,
                onClick = { onAction(RecoveryAction.OnRecoveryClick) },
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
