package dmitriy.losev.datastore.domain.usecases.integer

import androidx.datastore.preferences.core.intPreferencesKey
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository

class DataStoreRemoveIntegerUseCase(private val dataStoreRepository: DataStoreRepository) : DataStoreBaseUseCase() {

    suspend fun removeIntegerValue(key: String) {
        dataStoreRepository.removeValue(key = intPreferencesKey(name = key))
    }
}