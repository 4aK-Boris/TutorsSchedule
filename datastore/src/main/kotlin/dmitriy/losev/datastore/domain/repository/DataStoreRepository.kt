package dmitriy.losev.datastore.domain.repository

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {

    suspend fun <T: Any> setValue(key: Preferences.Key<T>, value: T)

    suspend fun <T: Any> getValue(key: Preferences.Key<T>, defaultValue: T): Flow<T>

    suspend fun <T: Any> removeValue(key: Preferences.Key<T>)

    suspend fun <T: Any> hasValue(key: Preferences.Key<T>): Flow<Boolean>
}