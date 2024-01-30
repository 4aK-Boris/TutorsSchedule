package dmitriy.losev.datastore.domain.usecases.string.set

import androidx.datastore.preferences.core.stringSetPreferencesKey
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository

class DataStoreSetStringSetUseCase(private val dataStoreRepository: DataStoreRepository) : DataStoreBaseUseCase() {

    suspend fun setStringSetValue(key: String, value: Set<String>) {
        dataStoreRepository.setValue(key = stringSetPreferencesKey(name = key), value = value)
    }
}