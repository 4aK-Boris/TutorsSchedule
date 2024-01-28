package dmitriy.losev.datastore.domain.usecases.bool

import androidx.datastore.preferences.core.booleanPreferencesKey
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class DataStoreHasBooleanUseCase(private val dataStoreRepository: DataStoreRepository) : DataStoreBaseUseCase() {

    suspend fun hasBooleanValue(key: String): Flow<Boolean> {
        return dataStoreRepository.hasValue(key = booleanPreferencesKey(name = key))
    }
}