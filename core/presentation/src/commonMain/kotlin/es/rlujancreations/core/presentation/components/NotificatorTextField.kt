package es.rlujancreations.core.presentation.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import es.rlujancreations.core.presentation.AppIcon
import es.rlujancreations.core.presentation.IconDisplay
import es.rlujancreations.core.presentation.theme.textFieldColor

/**
 * Created by Raúl L.C. on 28/3/25.
 * https://m3.material.io/components/text-fields/guidelines
 */
@Composable
fun NotificatorTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    leadingIcon: AppIcon? = null,
    title: String?,
    isEnabled: Boolean = true,
    trailingIcon: AppIcon? = null,
    error: String? = null,
    maxLines: Int = 1,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(
            autoCorrectEnabled = false,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        ),
    keyboardActions: KeyboardActions = KeyboardActions(),
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
        label = { Text(error ?: (title ?: "")) },
        trailingIcon = {
            IconDisplay(
                appIcon = trailingIcon,
                contentDescription = contentDescription,
                tint = if (error == null) textFieldColor else MaterialTheme.colorScheme.error,
            )
        },
        isError = error != null,
        maxLines = maxLines,
        minLines = minLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = backgroundColor,
            unfocusedContainerColor = if (isSystemInDarkTheme())
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
            unfocusedPlaceholderColor = textFieldColor.copy(alpha = 0.6f),
            unfocusedLabelColor = textFieldColor.copy(alpha = 0.5f),
            focusedTextColor = textFieldColor,
            unfocusedTextColor = textFieldColor.copy(alpha = 0.7f),
        ),
    )
}


@Composable
fun NotificatorOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    leadingIcon: AppIcon? = null,
    title: String?,
    isEnabled: Boolean = true,
    trailingIcon: AppIcon? = null,
    error: String? = null,
    maxLines: Int = 1,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions =
        KeyboardOptions(
            autoCorrectEnabled = false,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
        ),
    keyboardActions: KeyboardActions = KeyboardActions(),
    backgroundColor: Color = Color.White,
) {
    OutlinedTextField(
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
        label = { Text(error ?: (title ?: "")) },
        trailingIcon = {
            IconDisplay(
                appIcon = trailingIcon,
                contentDescription = contentDescription,
                tint = if (error == null) textFieldColor else MaterialTheme.colorScheme.error,
            )
        },
        isError = error != null,
        maxLines = maxLines,
        minLines = minLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = backgroundColor,
            unfocusedContainerColor = if (isSystemInDarkTheme())
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
            unfocusedLabelColor = textFieldColor.copy(alpha = 0.5f),
            focusedTextColor = textFieldColor,
            unfocusedTextColor = textFieldColor.copy(alpha = 0.7f),
        ),
    )
}
