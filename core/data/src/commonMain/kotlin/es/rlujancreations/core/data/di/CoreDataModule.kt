package es.rlujancreations.core.data.di

import es.rlujancreations.core.data.networking.HttpClientFactory
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 19/1/25.
 */

expect val kSecureStorageModule: Module

val coreDataModule =
    module {

        // Create a single instance of encrypted shared preferences to save token an inject it
//        single { HttpClientFactory(get()).build() }
        single { HttpClientFactory().build() }
    }
