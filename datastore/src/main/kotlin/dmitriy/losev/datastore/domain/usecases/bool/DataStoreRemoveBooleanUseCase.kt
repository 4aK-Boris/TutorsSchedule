package dmitriy.losev.datastore.domain.usecases.bool

import androidx.datastore.preferences.core.booleanPreferencesKey
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository

class DataStoreRemoveBooleanUseCase(private val dataStoreRepository: DataStoreRepository) : DataStoreBaseUseCase() {

    suspend fun removeBooleanValue(key: String) {
        dataStoreRepository.removeValue(key = booleanPreferencesKey(name = key))
    }
}