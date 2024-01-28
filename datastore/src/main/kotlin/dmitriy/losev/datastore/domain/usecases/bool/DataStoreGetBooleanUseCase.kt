package dmitriy.losev.datastore.domain.usecases.bool

import androidx.datastore.preferences.core.booleanPreferencesKey
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class DataStoreGetBooleanUseCase(private val dataStoreRepository: DataStoreRepository) : DataStoreBaseUseCase() {

    suspend fun getBooleanValue(key: String, defaultValue: Boolean): Flow<Boolean> {
        return dataStoreRepository.getValue(key = booleanPreferencesKey(name = key), defaultValue = defaultValue)
    }
}