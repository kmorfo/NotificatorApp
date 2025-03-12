package es.rlujancreations.core.presentation.di

import es.rlujancreations.core.presentation.GetStringProvider
import es.rlujancreations.core.presentation.StringProvider
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 12/3/25.
 */
actual val coreUiModule = module {
    single<StringProvider> { GetStringProvider() }
}
