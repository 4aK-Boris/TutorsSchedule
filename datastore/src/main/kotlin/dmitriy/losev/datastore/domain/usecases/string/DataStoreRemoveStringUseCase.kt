package dmitriy.losev.datastore.domain.usecases.string

import androidx.datastore.preferences.core.stringPreferencesKey
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository

class DataStoreRemoveStringUseCase(private val dataStoreRepository: DataStoreRepository) : DataStoreBaseUseCase() {

    suspend fun removeStringValue(key: String) {
        dataStoreRepository.removeValue(key = stringPreferencesKey(name = key))
    }
}