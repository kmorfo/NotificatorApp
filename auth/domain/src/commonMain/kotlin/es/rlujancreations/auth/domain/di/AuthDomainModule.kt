package es.rlujancreations.auth.domain.di

import es.rlujancreations.auth.domain.usecases.LoginUseCase
import es.rlujancreations.auth.domain.usecases.RecoveryUseCase
import es.rlujancreations.auth.domain.usecases.RegisterUseCase
import es.rlujancreations.auth.domain.validator.UserDataValidator
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 31/3/25.
 */
val authDomainModule =
    module {
        factoryOf(::UserDataValidator)

        singleOf(::LoginUseCase)
        singleOf(::RecoveryUseCase)
        singleOf(::RegisterUseCase)
    }
