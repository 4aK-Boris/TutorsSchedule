package dmitriy.losev.datastore.domain.usecases

import androidx.datastore.preferences.core.intPreferencesKey
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository

class DataStoreRemoveIntegerUseCase(
    errorHandler: ErrorHandler,
    private val dataStoreRepository: DataStoreRepository
) : DataStoreBaseUseCase(errorHandler) {

    suspend fun removeIntegerValue(key: String): Result<Unit> = safeCall {
        dataStoreRepository.removeValue(key = intPreferencesKey(name = key))
    }
}