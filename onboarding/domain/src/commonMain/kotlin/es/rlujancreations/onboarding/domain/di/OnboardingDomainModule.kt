package es.rlujancreations.onboarding.domain.di

import es.rlujancreations.onboarding.domain.usecases.CompleteOnboardingUseCase
import es.rlujancreations.onboarding.domain.usecases.HasSeenOnboardingUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 18/3/25.
 */
val onboardingDomainModule =
    module {
        singleOf(::CompleteOnboardingUseCase)
        singleOf(::HasSeenOnboardingUseCase)
    }
