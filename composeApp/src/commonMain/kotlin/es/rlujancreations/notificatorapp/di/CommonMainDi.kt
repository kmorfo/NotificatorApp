package es.rlujancreations.notificatorapp.di

import es.rlujancreations.core.data.di.coreDataModule
import es.rlujancreations.core.data.di.kSecureStorageModule
import es.rlujancreations.core.domain.di.coreDomainModule
import es.rlujancreations.core.presentation.di.coreUiModule
import es.rlujancreations.database.di.coreDatabaseModule
import es.rlujancreations.database.di.databaseNativeModule
import es.rlujancreations.home.presentation.di.homePresentationModule
import es.rlujancreations.onboarding.domain.di.onboardingDomainModule
import es.rlujancreations.onboarding.presentation.di.authPresentationModule
import es.rlujancreations.onboarding.presentation.di.onboardingPresentationModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

/**
 * Created by Raúl L.C. on 19/1/25.
 */

expect val commonNativeModules: List<Module>

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            authPresentationModule,
            databaseNativeModule,
            coreDatabaseModule,
            coreDataModule,
            coreDomainModule,
            coreUiModule,
            homePresentationModule,
            onboardingDomainModule,
            onboardingPresentationModule,
            kSecureStorageModule,
            *commonNativeModules.toTypedArray(),
        )
    }
}
