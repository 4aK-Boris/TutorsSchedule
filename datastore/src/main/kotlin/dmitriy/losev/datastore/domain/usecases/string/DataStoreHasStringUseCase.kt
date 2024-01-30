package dmitriy.losev.datastore.domain.usecases.string

import androidx.datastore.preferences.core.stringPreferencesKey
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class DataStoreHasStringUseCase(private val dataStoreRepository: DataStoreRepository) : DataStoreBaseUseCase() {

    suspend fun hasStringValue(key: String): Flow<Boolean> {
        return dataStoreRepository.hasValue(key = stringPreferencesKey(name = key))
    }
}