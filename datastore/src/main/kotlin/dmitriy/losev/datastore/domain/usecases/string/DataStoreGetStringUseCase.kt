package dmitriy.losev.datastore.domain.usecases.string

import androidx.datastore.preferences.core.stringPreferencesKey
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class DataStoreGetStringUseCase(private val dataStoreRepository: DataStoreRepository) : DataStoreBaseUseCase() {

    suspend fun getStringValue(key: String, defaultValue: String): Flow<String> {
        return dataStoreRepository.getValue(key = stringPreferencesKey(name = key), defaultValue = defaultValue)
    }
}