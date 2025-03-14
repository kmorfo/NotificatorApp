package es.rlujancreations.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Raúl L.C. on 19/1/25.
 */
actual class DatabaseFactory(
    private val context: Context,
) {
    actual fun create(): RoomDatabase.Builder<NotificatorDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(NotificatorDatabase.DB_NAME)

        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath,
        )
    }
}
