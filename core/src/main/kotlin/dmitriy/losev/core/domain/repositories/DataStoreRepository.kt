package dmitriy.losev.core.domain.repositories

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun setIntValue(key: String, value: Int)

    suspend fun getIntValue(key: String, defaultValue: Int): Flow<Int>

    suspend fun removeIntValue(key: String)

    suspend fun hasIntValue(key: String): Flow<Boolean>

    suspend fun setStringValue(key: String, value: String)

    suspend fun getStringValue(key: String, defaultValue: String): Flow<String>

    suspend fun removeStringValue(key: String)

    suspend fun hasStringValue(key: String): Flow<Boolean>
}