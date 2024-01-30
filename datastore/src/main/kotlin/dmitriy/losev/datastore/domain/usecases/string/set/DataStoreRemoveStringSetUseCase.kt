package dmitriy.losev.datastore.domain.usecases.string.set

import androidx.datastore.preferences.core.stringSetPreferencesKey
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository

class DataStoreRemoveStringSetUseCase(private val dataStoreRepository: DataStoreRepository) : DataStoreBaseUseCase() {

    suspend fun removeStringSetValue(key: String) {
        dataStoreRepository.removeValue(key = stringSetPreferencesKey(name = key))
    }
}