package es.rlujancreations.core.presentation

import java.awt.Toolkit

actual fun getScreenDimensions(): ScreenDimensions = object : ScreenDimensions {
    private val screenSize = Toolkit.getDefaultToolkit().screenSize

    override val screenWidthDp: Int =
        (screenSize.width / Toolkit.getDefaultToolkit().screenResolution * 160).toInt()
    override val screenHeightDp: Int =
        (screenSize.height / Toolkit.getDefaultToolkit().screenResolution * 160).toInt()

    override val windowWidthSizeClass: WindowWidthSizeClass = when {
        screenWidthDp < 600 -> WindowWidthSizeClass.Compact
        screenWidthDp < 840 -> WindowWidthSizeClass.Medium
        else -> WindowWidthSizeClass.Expanded
    }
}
