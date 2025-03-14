package es.rlujancreations.database.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import es.rlujancreations.core.domain.user.UserDataSourceLocal
import es.rlujancreations.core.domain.userPreferences.UserPreferencesDataSource
import es.rlujancreations.database.DatabaseFactory
import es.rlujancreations.database.Database
import es.rlujancreations.database.user.UserDataSourceLocalRoom
import es.rlujancreations.database.userPreferences.UserPreferencesDataSourceRoom
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Created by Raúl L.C. on 19/1/25.
 */

expect val databaseNativeModule: Module

val coreDatabaseModule =
    module {
        single {
            get<DatabaseFactory>().create()
                .setDriver(BundledSQLiteDriver())
                .build()
        }
        single { get<Database>().userPreferencesDAO }
        single { get<Database>().userDAO }

        singleOf(::UserDataSourceLocalRoom).bind<UserDataSourceLocal>()
        singleOf(::UserPreferencesDataSourceRoom).bind<UserPreferencesDataSource>()
    }
