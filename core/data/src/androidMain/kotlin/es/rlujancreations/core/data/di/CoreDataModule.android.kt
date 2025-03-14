package es.rlujancreations.core.data.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import es.rlujancreations.core.data.localstorage.KSecureStorage
import es.rlujancreations.core.domain.IKSecureStorage
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 19/1/25.
 */
actual val kSecureStorageModule =
    module {

        single<SharedPreferences> {
            val context = androidApplication()
            val masterKey =
                MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build()

            EncryptedSharedPreferences.create(
                context,
                "auth_pref",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
            )
        }

        single<IKSecureStorage> {
            KSecureStorage().apply {
                initialize(get())
            }
        }
    }
