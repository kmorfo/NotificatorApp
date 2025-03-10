package es.rlujancreations.notificatorapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import es.rlujancreations.notificatorapp.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "NotificatorApp",
    ) {
        App()
    }
}
