package es.rlujancreations.notificatorapp.di

import es.rlujancreations.core.data.di.coreDataModule
import es.rlujancreations.database.di.coreDatabaseModule
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
            coreDatabaseModule,
            coreDataModule,
            *commonNativeModules.toTypedArray(),
        )
    }
}
