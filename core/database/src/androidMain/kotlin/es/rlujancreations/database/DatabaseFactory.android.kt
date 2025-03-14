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
    actual fun create(): RoomDatabase.Builder<Database> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(Database.DB_NAME)

        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath,
        )
    }
}
