package dmitriy.losev.core.domain.usecases

import dmitriy.losev.core.domain.repositories.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class DataStoreUseCases(
    private val dataStoreRepository: DataStoreRepository
) {

    suspend fun setIntValue(key: String, value: Int) {
        dataStoreRepository.setIntValue(key = key, value = value)
    }

    suspend fun getIntValue(key: String, defaultValue: Int = 0): Flow<Int> {
        return dataStoreRepository.getIntValue(key = key, defaultValue = defaultValue)
    }

    suspend fun removeIntValue(key: String) {
        dataStoreRepository.removeIntValue(key = key)
    }

    suspend fun hasIntValue(key: String): Flow<Boolean> {
        return dataStoreRepository.hasIntValue(key = key)
    }

    suspend fun setStringValue(key: String, value: String) {
        dataStoreRepository.setStringValue(key = key, value = value)
    }

    suspend fun getStringValue(key: String, defaultValue: String = ""): Flow<String> {
        return dataStoreRepository.getStringValue(key = key, defaultValue = defaultValue)
    }

    suspend fun removeStringValue(key: String) {
        dataStoreRepository.removeStringValue(key = key)
    }

    suspend fun hasStringValue(key: String): Flow<Boolean> {
        return dataStoreRepository.hasIntValue(key = key)
    }
}