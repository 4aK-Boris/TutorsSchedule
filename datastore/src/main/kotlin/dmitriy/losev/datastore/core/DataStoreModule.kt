package dmitriy.losev.datastore.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dmitriy.losev.datastore.data.di.dataStoreRepositoryModule
import dmitriy.losev.datastore.domain.di.dataStoreUseCaseModule
import org.koin.dsl.module

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_FILE_NAME)

val dataStoreModule = module {

    single<DataStore<Preferences>> {
        get<Context>().dataStore
    }

    includes(dataStoreUseCaseModule, dataStoreRepositoryModule)
}