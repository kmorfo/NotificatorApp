package es.rlujancreations.core.presentation.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.layout

/**
 * Created by Raúl L.C. on 27/3/25.
 */
fun Modifier.rotateVertically(clockwise: Boolean = true): Modifier {

    val rotate = rotate(if (clockwise) 90f else -90f)

    val modifiedView = layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.height, placeable.width) {
            placeable.place(
                x = -(placeable.width / 2 - placeable.height / 2),
                y = -(placeable.height / 2 - placeable.width / 2),
            )
        }
    }

    return rotate then modifiedView
}
