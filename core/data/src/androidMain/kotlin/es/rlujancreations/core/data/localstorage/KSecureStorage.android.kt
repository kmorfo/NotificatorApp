package es.rlujancreations.core.data.localstorage

import android.content.SharedPreferences
import es.rlujancreations.core.domain.IKSecureStorage
import androidx.core.content.edit

/**
 * Created by Raúl L.C. on 13/3/25.
 */

actual class KSecureStorage actual constructor() : IKSecureStorage {
    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(sharedPreferences: SharedPreferences) {
        this.sharedPreferences = sharedPreferences
    }

    private fun checkInitialization() {
        if (!this::sharedPreferences.isInitialized) {
            throw IllegalStateException(
                "KSecureStorage is not initialized. Call initialize() first.",
            )
        }
    }

    override fun setItem(
        key: String,
        value: String?,
    ) {
        checkInitialization()
        editSharedPreferences {
            putString(key, value)
        }
    }

    override fun getItem(key: String): String? {
        checkInitialization()
        return sharedPreferences.getString(key, null)
    }

    override fun removeItem(key: String) {
        checkInitialization()
        editSharedPreferences {
            remove(key)
        }
    }

    override fun clear() {
        checkInitialization()
        editSharedPreferences {
            clear()
        }
    }

    private inline fun editSharedPreferences(action: SharedPreferences.Editor.() -> Unit) {
        sharedPreferences.edit() {
            action()
        }
    }
}
