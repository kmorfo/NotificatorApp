package es.rlujancreations.onboarding.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rlujancreations.core.presentation.IconDisplay
import es.rlujancreations.core.presentation.LogoIcon
import es.rlujancreations.core.presentation.WindowWidthSizeClass
import es.rlujancreations.core.presentation.extensions.rotateVertically
import es.rlujancreations.core.presentation.getScreenDimensions
import notificatorapp.auth.presentation.generated.resources.Res
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
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(15.dp),
                )
                .background(
                    MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(15.dp),
                ),
    ) {

    }
}
