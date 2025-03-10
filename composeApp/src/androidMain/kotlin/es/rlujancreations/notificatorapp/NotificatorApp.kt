package es.rlujancreations.notificatorapp

import android.app.Application
import es.rlujancreations.notificatorapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

/**
 * Created by Raúl L.C. on 19/1/25.
 */
class NotificatorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@NotificatorApp)
        }
    }
}
