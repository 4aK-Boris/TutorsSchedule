package dmitriy.losev.datastore.domain.usecases.integer

import androidx.datastore.preferences.core.intPreferencesKey
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository

class DataStoreSetIntegerUseCase(private val dataStoreRepository: DataStoreRepository) : DataStoreBaseUseCase() {

    suspend fun setIntegerValue(key: String, value: Int) {
        dataStoreRepository.setValue(key = intPreferencesKey(name = key), value = value)
    }
}