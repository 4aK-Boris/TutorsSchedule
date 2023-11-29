package dmitriy.losev.datastore.domain.usecases

import androidx.datastore.preferences.core.intPreferencesKey
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.datastore.core.DataStoreBaseUseCase
import dmitriy.losev.datastore.domain.repository.DataStoreRepository

class DataStoreSetIntegerUseCase(
    errorHandler: ErrorHandler,
    private val dataStoreRepository: DataStoreRepository
) : DataStoreBaseUseCase(errorHandler) {

    suspend fun setIntegerValue(key: String, value: Int): Result<Unit> = safeCall {
        dataStoreRepository.setValue(key = intPreferencesKey(name = key), value = value)
    }
}