package es.rlujancreations.core.domain.di

import es.rlujancreations.core.domain.userPreferences.usecases.GetUserPreferencesUseCase
import es.rlujancreations.core.domain.userPreferences.usecases.SetUserPreferencesUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 14/3/25.
 */
val coreDomainModule = module {
    singleOf(::SetUserPreferencesUseCase)
    singleOf(::GetUserPreferencesUseCase)
}
