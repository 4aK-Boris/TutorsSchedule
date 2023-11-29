package dmitriy.losev.datastore.domain.usecases

import androidx.datastore.preferences.core.intPreferencesKey
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class DataStoreGetIntegerUseCase(
    errorHandler: ErrorHandler,
    private val dataStoreRepository: DataStoreRepository
) : DataStoreBaseUseCase(errorHandler) {

    suspend fun getIntegerValue(key: String, defaultValue: Int): Result<Flow<Int>> = safeCall {
        dataStoreRepository.getValue(key = intPreferencesKey(name = key), defaultValue = defaultValue)
    }
}