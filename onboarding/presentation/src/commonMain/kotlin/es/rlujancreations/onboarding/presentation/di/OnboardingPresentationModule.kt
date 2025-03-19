package es.rlujancreations.onboarding.presentation.di

import es.rlujancreations.onboarding.presentation.OnboardingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 18/3/25.
 */

val onboardingPresentationModule =
    module {
        viewModelOf(::OnboardingViewModel)
    }
