package dmitriy.losev.datastore.domain.usecases.bool

import androidx.datastore.preferences.core.booleanPreferencesKey
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository

class DataStoreSetBooleanUseCase(private val dataStoreRepository: DataStoreRepository) : DataStoreBaseUseCase() {

    suspend fun setBooleanValue(key: String, value: Boolean) {
        dataStoreRepository.setValue(key = booleanPreferencesKey(name = key), value = value)
    }
}