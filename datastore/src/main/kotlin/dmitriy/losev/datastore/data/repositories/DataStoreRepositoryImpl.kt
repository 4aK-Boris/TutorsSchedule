package dmitriy.losev.datastore.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import dmitriy.losev.datastore.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
): DataStoreRepository {

    override suspend fun <T: Any> setValue(key: Preferences.Key<T>, value: T) {
        dataStore.edit { settings ->
            settings[key] = value
        }
    }

    override suspend fun <T: Any> getValue(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return dataStore.data.map { preferences ->
            preferences[key] ?: defaultValue
        }
    }

    override suspend fun <T: Any> removeValue(key: Preferences.Key<T>) {
        dataStore.edit { preferences ->
            preferences.remove(key = key)
        }
    }

    override suspend fun <T: Any> hasValue(key: Preferences.Key<T>): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences.contains(key = key)
        }
    }
}