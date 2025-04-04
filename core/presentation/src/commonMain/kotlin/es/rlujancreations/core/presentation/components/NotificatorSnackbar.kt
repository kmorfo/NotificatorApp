package es.rlujancreations.core.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Raúl L.C. on 17/3/25.
 */
@Composable
fun MySnackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
) {
    val message: String = snackbarData.visuals.message

    val containerColor =
        if (message.contains("error", ignoreCase = true) ||
            message.contains("incorr", ignoreCase = true)
        ) {
            MaterialTheme.colorScheme.error
        } else {
            Color(0xFF309c5f)
        }

    Snackbar(
        containerColor = containerColor,
        contentColor = Color.White,
        shape = RoundedCornerShape(3.dp),
        modifier = modifier.padding(horizontal = 2.dp, vertical = 32.dp),
    ) {
        Text(
            text = message,
            fontSize = 16.sp,
        )
    }
}
