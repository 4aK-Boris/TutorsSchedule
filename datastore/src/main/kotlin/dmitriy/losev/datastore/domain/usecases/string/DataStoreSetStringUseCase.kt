package dmitriy.losev.datastore.domain.usecases.string

import androidx.datastore.preferences.core.stringPreferencesKey
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository

class DataStoreSetStringUseCase(private val dataStoreRepository: DataStoreRepository) : DataStoreBaseUseCase() {

    suspend fun setStringValue(key: String, value: String) {
        dataStoreRepository.setValue(key = stringPreferencesKey(name = key), value = value)
    }
}