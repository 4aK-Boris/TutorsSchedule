package dmitriy.losev.datastore.domain.usecases.string.set

import androidx.datastore.preferences.core.stringSetPreferencesKey
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class DataStoreGetStringSetUseCase(private val dataStoreRepository: DataStoreRepository) : DataStoreBaseUseCase() {

    suspend fun getStringSetValue(key: String, defaultValue: Set<String>): Flow<Set<String>> {
        return dataStoreRepository.getValue(key = stringSetPreferencesKey(name = key), defaultValue = defaultValue)
    }
}