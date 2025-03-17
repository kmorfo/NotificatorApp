package es.rlujancreations.home.presentation.di

import es.rlujancreations.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 17/3/25.
 */
val homePresentationModule = module {
    viewModelOf(::HomeViewModel)
}
