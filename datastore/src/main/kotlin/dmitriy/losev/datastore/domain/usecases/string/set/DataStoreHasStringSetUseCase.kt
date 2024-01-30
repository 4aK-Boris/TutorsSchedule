package dmitriy.losev.datastore.domain.usecases.string.set

import androidx.datastore.preferences.core.stringSetPreferencesKey
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class DataStoreHasStringSetUseCase(private val dataStoreRepository: DataStoreRepository) : DataStoreBaseUseCase() {

    suspend fun hasStringSetValue(key: String): Flow<Boolean> {
        return dataStoreRepository.hasValue(key = stringSetPreferencesKey(name = key))
    }
}