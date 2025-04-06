package es.rlujancreations.core.presentation

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.UIKit.UIScreen

class IOSScreenDimensions : ScreenDimensions {
    @OptIn(ExperimentalForeignApi::class)
    override val screenWidthDp: Int
        get() = (UIScreen.mainScreen.bounds.useContents { size.width }).toInt()

    @OptIn(ExperimentalForeignApi::class)
    override val screenHeightDp: Int
        get() = (UIScreen.mainScreen.bounds.useContents { size.height } - 65).toInt()

    override val windowWidthSizeClass: WindowWidthSizeClass
        get() =
            when {
                screenWidthDp >= 840 -> WindowWidthSizeClass.Expanded
                screenWidthDp >= 600 -> WindowWidthSizeClass.Medium
                else -> WindowWidthSizeClass.Compact
            }
}

actual fun getScreenDimensions(): ScreenDimensions {
    return IOSScreenDimensions()
}
