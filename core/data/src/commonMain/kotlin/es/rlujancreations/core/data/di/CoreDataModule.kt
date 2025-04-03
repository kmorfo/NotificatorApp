package es.rlujancreations.core.data.di

import es.rlujancreations.core.data.auth.EncryptedSessionStorage
import es.rlujancreations.core.data.networking.HttpClientFactory
import es.rlujancreations.core.domain.SessionStorage
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 19/1/25.
 */

expect val kSecureStorageModule: Module

val coreDataModule =
    module {
        singleOf(::EncryptedSessionStorage).bind<SessionStorage>()

//        single { HttpClientFactory(get()).build() }
        single { HttpClientFactory().build() }
    }
