package es.rlujancreations.auth.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rlujancreations.core.presentation.EmailIcon
import es.rlujancreations.core.presentation.IconDisplay
import androidx.compose.foundation.rememberScrollState
import es.rlujancreations.core.presentation.LogoIcon
import es.rlujancreations.core.presentation.LogoRLujanIcon
import es.rlujancreations.core.presentation.Shapes
import es.rlujancreations.core.presentation.UserIcon
import es.rlujancreations.core.presentation.WindowWidthSizeClass
import es.rlujancreations.core.presentation.components.MySnackbar
import es.rlujancreations.core.presentation.components.NotificatorActionButton
import es.rlujancreations.core.presentation.components.NotificatorPasswordTextField
import es.rlujancreations.core.presentation.components.NotificatorTextField
import es.rlujancreations.core.presentation.components.RegisterRequirements
import es.rlujancreations.core.presentation.extensions.rotateVertically
import es.rlujancreations.core.presentation.getScreenDimensions
import kotlinx.coroutines.flow.Flow
import notificatorapp.auth.presentation.generated.resources.Res
import notificatorapp.auth.presentation.generated.resources.already_have_an_account
import notificatorapp.auth.presentation.generated.resources.at_least_one_number
import notificatorapp.auth.presentation.generated.resources.btn_login
import notificatorapp.auth.presentation.generated.resources.btn_register
import notificatorapp.auth.presentation.generated.resources.cd_confirmation_password
import notificatorapp.auth.presentation.generated.resources.cd_email
import notificatorapp.auth.presentation.generated.resources.cd_logo_rlc
import notificatorapp.auth.presentation.generated.resources.cd_password
import notificatorapp.auth.presentation.generated.resources.cd_username
import notificatorapp.auth.presentation.generated.resources.contains_lowercase_char
import notificatorapp.auth.presentation.generated.resources.contains_uppercase_char
import notificatorapp.auth.presentation.generated.resources.create_account
import notificatorapp.auth.presentation.generated.resources.email
import notificatorapp.auth.presentation.generated.resources.example_email
import notificatorapp.auth.presentation.generated.resources.example_username
import notificatorapp.auth.presentation.generated.resources.must_be_a_valid_email
import notificatorapp.auth.presentation.generated.resources.must_be_a_valid_username
import notificatorapp.auth.presentation.generated.resources.password
import notificatorapp.auth.presentation.generated.resources.password_confirmation
import notificatorapp.auth.presentation.generated.resources.username
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegisterScreenRoot(
    onLoginClick: () -> Unit,
    onSuccessfulRegistration: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    RegisterScreen(
        state = state,
        modifier = modifier,
        snackbarHostState = viewModel.snackbarHostState,
        onAction = { action ->
            when (action) {
                is RegisterAction.OnLoginClick -> onLoginClick()
                else -> Unit
            }
            viewModel.onAction(action)
        },
        events = viewModel.events,
        onSuccessfulRegistration = onSuccessfulRegistration,
    )
}

@Composable
fun RegisterScreen(
    state: RegisterState,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
    events: Flow<RegisterEvent>,
    onAction: (RegisterAction) -> Unit,
    onSuccessfulRegistration: () -> Unit,
) {
    val windowWidthSizeClass = getScreenDimensions().windowWidthSizeClass

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { snackbarData ->
                MySnackbar(snackbarData)
            }
        },
    ) { paddingValues ->
        when (windowWidthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)),
                ) {
                    Column(
                        modifier = Modifier.align(alignment = Alignment.TopCenter),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        IconDisplay(LogoIcon(), modifier = Modifier.size(150.dp))
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
                    RegisterForm(
                        state = state,
                        onAction = onAction,
                        modifier = Modifier.align(alignment = Alignment.BottomCenter),
                    )
                }
            }

            WindowWidthSizeClass.Medium -> {
                Column(
                    modifier =
                        modifier
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
                    RegisterForm(
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
                        modifier
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
                    RegisterForm(
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
private fun RegisterForm(
    state: RegisterState,
    onAction: (RegisterAction) -> Unit,
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
        if (windowWidthSizeClass != WindowWidthSizeClass.Compact)
            IconDisplay(
                LogoRLujanIcon(),
                contentDescription = stringResource(Res.string.cd_logo_rlc),
                modifier =
                    Modifier
                        .padding(top = 32.dp)
                        .widthIn(min = 120.dp, max = 250.dp),
            )
        else
            Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
        ) {
            Text(
                text = stringResource(Res.string.create_account),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = 8.dp),
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
                        onAction(RegisterAction.OnLoginClick)
                    },
            )
            Spacer(modifier = Modifier.height(16.dp))
            NotificatorTextField(
                value = state.email,
                onValueChange = { onAction(RegisterAction.OnEmailChange(it)) },
                placeholder = stringResource(Res.string.example_email),
                contentDescription = stringResource(Res.string.cd_email),
                title = stringResource(Res.string.email),
                leadingIcon = EmailIcon(),
                trailingIcon = null,
                error = state.emailError?.let { stringResource(it) },
                maxLines = 1,
                minLines = 1,
            )
            Spacer(modifier = Modifier.height(16.dp))
            NotificatorTextField(
                value = state.username,
                onValueChange = { onAction(RegisterAction.OnUsernameChange(it)) },
                placeholder = stringResource(Res.string.example_username),
                contentDescription = stringResource(Res.string.cd_username),
                title = stringResource(Res.string.username),
                leadingIcon = UserIcon(),
                trailingIcon = null,
                error = state.usernameError?.let { stringResource(it) },
                maxLines = 1,
                minLines = 1,
            )
            Spacer(modifier = Modifier.height(16.dp))
            NotificatorPasswordTextField(
                value = state.password,
                onValueChange = { onAction(RegisterAction.OnPasswordChange(it)) },
                contentDescription = stringResource(Res.string.cd_password),
                title = stringResource(Res.string.password),
                parentTitle = stringResource(Res.string.create_account),
                isPasswordVisible = state.isPasswordVisible,
                keyboardOptions =
                    KeyboardOptions(
                        autoCorrectEnabled = false,
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next,
                    ),
                onTogglePasswordVisibility = {
                    onAction(
                        RegisterAction.OnTogglePasswordVisibility,
                    )
                },
            )
            Spacer(modifier = Modifier.height(8.dp))
            NotificatorPasswordTextField(
                value = state.passwordConfirmation,
                onValueChange = { onAction(RegisterAction.OnPasswordConfirmationChange(it)) },
                contentDescription = stringResource(Res.string.cd_confirmation_password),
                parentTitle = stringResource(Res.string.create_account),
                title = stringResource(Res.string.password_confirmation),
                isPasswordVisible = state.isPasswordConfirmationVisible,
                keyboardActions =
                    KeyboardActions(
                        onAny = {
                            focusManager.clearFocus()
                            if (state.canRegister) onAction(RegisterAction.OnRegisterClick)
                        },
                    ),
                onTogglePasswordVisibility = {
                    onAction(
                        RegisterAction.OnTogglePasswordConfirmationVisibility,
                    )
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            RegisterRequirements(
                text = stringResource(Res.string.must_be_a_valid_email),
                isValid = state.emailError == null && state.email.isNotBlank(),
            )
            Spacer(modifier = Modifier.height(4.dp))
            RegisterRequirements(
                text = stringResource(Res.string.must_be_a_valid_username),
                isValid = state.usernameError == null && state.username.isNotBlank(),
            )
            Spacer(modifier = Modifier.height(4.dp))
            RegisterRequirements(
                text = stringResource(Res.string.at_least_one_number),
                isValid = state.passwordValidationState.hasNumber,
            )
            Spacer(modifier = Modifier.height(4.dp))
            RegisterRequirements(
                text = stringResource(Res.string.contains_lowercase_char),
                isValid = state.passwordValidationState.hasLowerCaseCharacter,
            )
            Spacer(modifier = Modifier.height(4.dp))
            RegisterRequirements(
                text = stringResource(Res.string.contains_uppercase_char),
                isValid = state.passwordValidationState.hasUpperCaseCharacter,
            )
            Spacer(modifier = Modifier.height(4.dp))
            RegisterRequirements(
                text = stringResource(Res.string.password_confirmation),
                isValid =
                    (state.password == state.passwordConfirmation) &&
                        state.password.isNotBlank(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            NotificatorActionButton(
                text = stringResource(Res.string.btn_register),
                isLoading = state.isRegistering,
                enabled = state.canRegister,
                onClick = { onAction(RegisterAction.OnLoginClick) },
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
