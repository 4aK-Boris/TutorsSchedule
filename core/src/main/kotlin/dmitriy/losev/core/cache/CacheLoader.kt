package dmitriy.losev.core.cache

import dmitriy.losev.core.asyncOnIO
import kotlinx.coroutines.coroutineScope

interface CacheLoader {

    suspend fun <T : Any> loadData(
        loadFromFirebase: suspend () -> T,
        loadFromCache: suspend () -> T?,
        saveToCache: suspend (T) -> Unit,
        onFirebaseLoading: (T) -> Unit,
        onCacheLoading: (T?) -> Unit
    ): Unit = coroutineScope {
        val firebaseJob = asyncOnIO { loadFromFirebase() }
        val cacheJob = asyncOnIO { loadFromCache() }
        val cacheData = cacheJob.await()
        onCacheLoading(cacheData)
        val firebaseData = firebaseJob.await()
        if (firebaseData != cacheData) {
            onFirebaseLoading(firebaseData)
            saveToCache(firebaseData)
        }
    }

    suspend fun updateData(
        updateInFirebase: suspend () -> Unit,
        updateInCache: suspend () -> Unit
    ): Unit = coroutineScope {
        updateInFirebase()
        updateInCache()
    }
}