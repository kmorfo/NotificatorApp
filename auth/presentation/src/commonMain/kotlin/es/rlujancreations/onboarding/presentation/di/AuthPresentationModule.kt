package es.rlujancreations.onboarding.presentation.di

import es.rlujancreations.onboarding.presentation.login.LoginViewModel
import es.rlujancreations.onboarding.presentation.recovery.RecoveryViewModel
import es.rlujancreations.onboarding.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 18/3/25.
 */
val authPresentationModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::RecoveryViewModel)
    viewModelOf(::RegisterViewModel)
}
