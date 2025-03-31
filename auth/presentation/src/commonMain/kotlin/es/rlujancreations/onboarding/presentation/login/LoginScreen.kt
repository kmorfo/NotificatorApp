package es.rlujancreations.onboarding.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rlujancreations.core.presentation.EmailIcon
import es.rlujancreations.core.presentation.IconDisplay
import es.rlujancreations.core.presentation.LockIcon
import es.rlujancreations.core.presentation.LogoIcon
import es.rlujancreations.core.presentation.LogoRLujanIcon
import es.rlujancreations.core.presentation.Shapes
import es.rlujancreations.core.presentation.WindowWidthSizeClass
import es.rlujancreations.core.presentation.components.NotificatorActionButton
import es.rlujancreations.core.presentation.components.NotificatorOutlinedTextField
import es.rlujancreations.core.presentation.components.NotificatorPasswordTextField
import es.rlujancreations.core.presentation.components.NotificatorTextField
import es.rlujancreations.core.presentation.extensions.rotateVertically
import es.rlujancreations.core.presentation.getScreenDimensions
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreen(
        state = state,
        onAction = viewModel::onAction,
        modifier = modifier,
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val windowWidthSizeClass = getScreenDimensions().windowWidthSizeClass

    when (windowWidthSizeClass) {
        WindowWidthSizeClass.Compact -> {
//            Column(
//                modifier =
//                    modifier
//                        .fillMaxSize()
//                        .background(MaterialTheme.colorScheme.background),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center,
//            ) {
//                Column(
//                    modifier = Modifier.weight(0.25f),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center,
//                ) {
//                    IconDisplay(LogoIcon())
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Column(
//                            horizontalAlignment = Alignment.End,
//                        ) {
//                            Text(
//                                text = "NotificatorAPP",
//                                fontSize = 22.sp,
//                                fontWeight = FontWeight.SemiBold,
//                            )
//                            Text(
//                                text = "Terminal".uppercase(),
//                                fontSize = 30.sp,
//                                fontWeight = FontWeight.Bold,
//                            )
//                        }
//                        Text(
//                            text = "PRO",
//                            fontSize = 28.sp,
//                            fontWeight = FontWeight.Bold,
//                            modifier = Modifier.rotateVertically(),
//                        )
//                    }
//                }
//                LoginForm(
//                    state = state,
//                    onAction = onAction,
//                    modifier = Modifier.weight(0.5f),
//                )
//                Spacer(modifier = Modifier.weight(0.04f))
//            }
        }

        WindowWidthSizeClass.Medium -> {
//            Column(
//                modifier =
//                    modifier
//                        .fillMaxSize()
//                        .background(Color.White)
//                        .padding(top = 16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center,
//            ) {
//                Column(
//                    modifier = Modifier.weight(0.25f),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center,
//                ) {
//                    IconDisplay(LogoIcon())
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//
//                        Text(
//                            text = "Notificator",
//                            fontSize = 28.sp,
//                            fontWeight = FontWeight.SemiBold,
//                        )
//                        Text(
//                            text = "APP",
//                            fontSize = 32.sp,
//                            fontWeight = FontWeight.Bold,
//                            modifier = Modifier.rotateVertically(),
//                        )
//                    }
//                }
//                LoginForm(
//                    state = state,
//                    onAction = onAction,
//                    modifier = Modifier.weight(0.5f),
//                )
//                Spacer(modifier = Modifier.weight(0.06f))
//            }
        }

        WindowWidthSizeClass.Expanded -> {
            Row(
                modifier =
                    modifier
                        .fillMaxSize()
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
                                fontSize = 70.sp,
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
                LoginForm(
                    state = state,
                    onAction = onAction,
                    modifier = Modifier.weight(0.5f),
                )
            }
        }
    }

}


@Composable
private fun LoginForm(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
    modifier: Modifier,
) {
    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier =
            modifier
                .padding(16.dp)
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
        IconDisplay(
            LogoRLujanIcon(),
            contentDescription = "RLujanCreations Logo",
            modifier =
                Modifier
                    .padding(top = 32.dp)
                    .widthIn(min = 120.dp, max = 250.dp),
        )
        Column(
            modifier = Modifier.padding(32.dp),
        ) {
            NotificatorTextField(
                value = state.email,
                onValueChange = { onAction(LoginAction.OnEmailChange(it)) },
                placeholder = "Email",
                contentDescription = "Admin email",
                title = "Email",
                leadingIcon = EmailIcon(),
                trailingIcon = null,
                error = state.emailError,
                maxLines = 1,
                minLines = 1,
            )
            Spacer(modifier = Modifier.height(16.dp))
            NotificatorPasswordTextField(
                value = state.password,
                onValueChange = { onAction(LoginAction.OnPasswordChange(it)) },
                contentDescription = "Password field",
                title = "Contraseña",
                isPasswordVisible = state.isPasswordVisible,
                keyboardActions = KeyboardActions(
                    onAny = {
                        focusManager.clearFocus()
                        if (state.canLogin) onAction(LoginAction.OnLoginClick)
                    },
                ),
                onTogglePasswordVisibility = { onAction(LoginAction.OnTogglePasswordVisibility) },
            )
            Spacer(modifier = Modifier.height(24.dp))
            NotificatorActionButton(
                text = "Login",
                isLoading = state.isLoggingIn,
                enabled = state.canLogin,
                onClick = { onAction(LoginAction.OnLoginClick) },
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
