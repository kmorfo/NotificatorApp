package es.rlujancreations.core.data.di

import es.rlujancreations.core.data.localstorage.KSecureStorage
import es.rlujancreations.core.domain.IKSecureStorage
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 19/1/25.
 */
actual val kSecureStorageModule =
    module {
        single<IKSecureStorage> { KSecureStorage() }
    }
