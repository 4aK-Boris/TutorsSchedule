package dmitriy.losev.core.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import dmitriy.losev.core.core.DATASTORE_FILE_NAME
import org.koin.dsl.module

private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = DATASTORE_FILE_NAME)

val dataStoreModule = module {

    single<DataStore<androidx.datastore.preferences.core.Preferences>> {
        get<Context>().dataStore
    }
}