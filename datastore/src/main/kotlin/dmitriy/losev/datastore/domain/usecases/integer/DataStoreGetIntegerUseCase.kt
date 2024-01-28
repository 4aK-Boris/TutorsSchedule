package dmitriy.losev.datastore.domain.usecases.integer

import androidx.datastore.preferences.core.intPreferencesKey
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class DataStoreGetIntegerUseCase(private val dataStoreRepository: DataStoreRepository) : DataStoreBaseUseCase() {

    suspend fun getIntegerValue(key: String, defaultValue: Int): Flow<Int> {
        return dataStoreRepository.getValue(key = intPreferencesKey(name = key), defaultValue = defaultValue)
    }
}