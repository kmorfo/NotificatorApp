package es.rlujancreations.notificatorapp

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import es.rlujancreations.notificatorapp.di.initKoin

fun main() = application {
    val state = rememberWindowState(width = 1200.dp, height = 900.dp)
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "NotificatorApp",
        state = state,
    ) {
        App()
    }
}
