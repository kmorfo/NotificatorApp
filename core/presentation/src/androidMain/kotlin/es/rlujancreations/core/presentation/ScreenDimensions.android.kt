package es.rlujancreations.core.presentation

import android.content.Context
import android.util.DisplayMetrics
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AndroidScreenDimensions(context: Context) : ScreenDimensions {
    private val displayMetrics: DisplayMetrics = context.resources.displayMetrics

    override val screenWidthDp: Int
        get() = (displayMetrics.widthPixels / displayMetrics.density).toInt()

    override val screenHeightDp: Int
        get() = (displayMetrics.heightPixels / displayMetrics.density).toInt()

    override val windowWidthSizeClass: WindowWidthSizeClass
        get() =
            when {
                screenWidthDp < 600 -> WindowWidthSizeClass.Compact
                screenWidthDp < 840 -> WindowWidthSizeClass.Medium
                else -> WindowWidthSizeClass.Expanded
            }
}

object ScreenDimensionsProvider : KoinComponent {
    private val context: Context by inject()
    val screenDimensions = AndroidScreenDimensions(context)
}

actual fun getScreenDimensions(): ScreenDimensions {
    return ScreenDimensionsProvider.screenDimensions
}
