package es.rlujancreations.core.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import es.rlujancreations.core.presentation.AppIcon
import es.rlujancreations.core.presentation.EyeClosedIcon
import es.rlujancreations.core.presentation.EyeOpenedIcon
import es.rlujancreations.core.presentation.IconDisplay
import es.rlujancreations.core.presentation.LockIcon
import es.rlujancreations.core.presentation.theme.textFieldColor
import notificatorapp.core.presentation.generated.resources.Res
import notificatorapp.core.presentation.generated.resources.hide_password
import notificatorapp.core.presentation.generated.resources.show_password
import org.jetbrains.compose.resources.stringResource

/**
 * Created by Raúl L.C. on 28/3/25.
 */

@Composable
fun NotificatorPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
    title: String?,
    parentTitle: String = "",
    placeholder: String = "********",
    leadingIcon: AppIcon? = LockIcon(),
    isPasswordVisible: Boolean = false,
    isEnabled: Boolean = true,
    error: String? = null,
    maxLines: Int = 1,
    minLines: Int = 1,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(
            autoCorrectEnabled = false,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
    onTogglePasswordVisibility: () -> Unit,
    backgroundColor: Color = Color.White,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text(text = placeholder) },
        leadingIcon = {
            IconDisplay(
                appIcon = leadingIcon,
                contentDescription = contentDescription,
                tint = if (error == null) textFieldColor else MaterialTheme.colorScheme.error,
            )
        },
        enabled = isEnabled,
        visualTransformation =
            if (!isPasswordVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
        label = { Text(error ?: (title ?: "")) },
        trailingIcon = {
            IconButton(onClick = onTogglePasswordVisibility) {
                IconDisplay(
                    if (!isPasswordVisible) EyeClosedIcon() else EyeOpenedIcon(),
                    contentDescription =
                        "$parentTitle " +
                            if (isPasswordVisible) {
                                stringResource(resource = Res.string.hide_password)
                            } else {
                                stringResource(resource = Res.string.show_password)
                            },
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        },
        isError = error != null,
        maxLines = maxLines,
        minLines = minLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors =
            TextFieldDefaults.colors(
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor =
                    if (isSystemInDarkTheme()) {
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    } else {
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
                    },
                unfocusedPlaceholderColor = textFieldColor.copy(alpha = 0.6f),
                unfocusedLabelColor = textFieldColor.copy(alpha = 0.5f),
                focusedTextColor = textFieldColor,
                unfocusedTextColor = textFieldColor.copy(alpha = 0.7f),
            ),
    )
}
