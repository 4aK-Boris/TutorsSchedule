package dmitriy.losev.datastore.domain.usecases.integer

import androidx.datastore.preferences.core.intPreferencesKey
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class DataStoreHasIntegerUseCase(private val dataStoreRepository: DataStoreRepository) : DataStoreBaseUseCase() {

    suspend fun hasIntegerValue(key: String): Flow<Boolean> {
        return dataStoreRepository.hasValue(key = intPreferencesKey(name = key))
    }
}