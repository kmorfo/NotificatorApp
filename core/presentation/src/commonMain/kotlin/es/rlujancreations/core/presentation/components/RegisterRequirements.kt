package es.rlujancreations.core.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.rlujancreations.core.presentation.CheckIcon
import es.rlujancreations.core.presentation.CrossIcon
import es.rlujancreations.core.presentation.IconDisplay

/**
 * Created by Raúl L.C. on 2/4/25.
 */

@Composable
fun RegisterRequirements(
    text: String,
    isValid: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconDisplay(
            if (isValid) CheckIcon() else CrossIcon(),
            contentDescription = null,
            tint = if (isValid) Color.Green else MaterialTheme.colorScheme.error,
        )
        Spacer(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 14.sp,
        )
    }
}
