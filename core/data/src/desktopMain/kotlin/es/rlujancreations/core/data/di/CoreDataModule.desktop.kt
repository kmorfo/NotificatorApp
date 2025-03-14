package es.rlujancreations.core.data.di

import es.rlujancreations.core.data.localstorage.KSecureStorage
import es.rlujancreations.core.domain.IKSecureStorage
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 19/1/25.
 */
actual val kSecureStorageModule = module {

    single {
        mapOf(
            "nodeName" to "NotificatorAPP/secureStorage",
            "password" to System.getenv("APP_SECURE_STORAGE_PASSWORD"),
        )
    }

    single<IKSecureStorage> {
        val config = get<Map<String, String>>()
        KSecureStorage().apply {
            initialize(
                nodeName = config["nodeName"] ?: "NotificatorAPP/secureStorage",
                password = config["password"] ?: "default_password",
            )
        }
    }
}
