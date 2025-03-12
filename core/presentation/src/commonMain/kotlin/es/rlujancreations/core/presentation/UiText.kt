package es.rlujancreations.core.presentation

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource

/**
 * Created by Raúl L.C. on 12/3/25.
 */
sealed interface UiText {
    data class DynamicString(val value: String) : UiText

    class StringResource(
        val resId: org.jetbrains.compose.resources.StringResource,
        val args: Array<Any> = arrayOf(),
    ) : UiText

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(resId, *args)
        }
    }

    fun asString(stringProvider: StringProvider): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringProvider.getString(resId.key, *args)
        }
    }
}
