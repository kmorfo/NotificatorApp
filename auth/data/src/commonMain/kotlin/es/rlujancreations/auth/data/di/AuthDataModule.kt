package es.rlujancreations.auth.data.di

import es.rlujancreations.auth.data.AuthRepositoryKtor
import es.rlujancreations.auth.domain.repository.AuthRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 3/4/25.
 */
val authDataModule =
    module {
        singleOf(::AuthRepositoryKtor) { bind<AuthRepository>() }
    }
