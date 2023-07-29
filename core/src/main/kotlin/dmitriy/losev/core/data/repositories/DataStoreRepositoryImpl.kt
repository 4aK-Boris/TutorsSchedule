package dmitriy.losev.core.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import dmitriy.losev.core.domain.repositories.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
): DataStoreRepository {

    override suspend fun setIntValue(key: String, value: Int) {
        setValue(key = intPreferencesKey(name = key), value = value)
    }

    override suspend fun getIntValue(key: String, defaultValue: Int): Flow<Int> {
        return getValue(key = intPreferencesKey(name = key), defaultValue = defaultValue)
    }

    override suspend fun removeIntValue(key: String) {
        removeValue(key = intPreferencesKey(name = key))
    }

    override suspend fun hasIntValue(key: String): Flow<Boolean> {
        return hasValue(key = intPreferencesKey(name = key))
    }

    override suspend fun setStringValue(key: String, value: String) {
        setValue(key = stringPreferencesKey(name = key), value = value)
    }

    override suspend fun getStringValue(key: String, defaultValue: String): Flow<String> {
        return getValue(key = stringPreferencesKey(name = key), defaultValue = defaultValue)
    }

    override suspend fun removeStringValue(key: String) {
        removeValue(key = stringPreferencesKey(name = key))
    }

    override suspend fun hasStringValue(key: String): Flow<Boolean> {
        return hasValue(key = intPreferencesKey(name = key))
    }

    private suspend fun <T: Any> setValue(key: Preferences.Key<T>, value: T) {
        dataStore.edit { settings ->
            settings[key] = value
        }
    }

    private fun <T: Any> getValue(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return dataStore.data.map { preferences ->
            preferences[key] ?: defaultValue
        }
    }

    private suspend fun <T: Any> removeValue(key: Preferences.Key<T>) {
        dataStore.edit { preferences ->
            preferences.remove(key = key)
        }
    }

    private suspend fun <T: Any> hasValue(key: Preferences.Key<T>): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences.contains(key = key)
        }
    }
}