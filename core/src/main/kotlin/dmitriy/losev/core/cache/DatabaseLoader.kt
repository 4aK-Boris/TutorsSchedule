package dmitriy.losev.core.cache

import dmitriy.losev.core.asyncOnIO
import dmitriy.losev.core.launchOnIO
import dmitriy.losev.core.models.BaseModel
import kotlinx.coroutines.coroutineScope

interface DatabaseLoader {

    suspend fun <T : BaseModel> loadData(
        loadFromFirebase: suspend () -> T,
        loadFromDatabase: suspend () -> T?,
        saveToDatabase: suspend (T) -> Unit,
        onFirebaseLoading: (T) -> Unit,
        onDatabaseLoading: (T?) -> Unit
    ): Unit = coroutineScope {
        val firebaseJob = asyncOnIO { loadFromFirebase() }
        val databaseJob = asyncOnIO { loadFromDatabase() }
        val databaseData = databaseJob.await()
        onDatabaseLoading(databaseData)
        val firebaseData = firebaseJob.await()
        if (firebaseData != databaseData) {
            onFirebaseLoading(firebaseData)
            saveToDatabase(firebaseData)
        }
    }

    suspend fun <T> loadAllData(
        loadFromFirebase: suspend () -> List<T>,
        loadFromDatabase: suspend () -> List<T>,
        saveToDatabase: suspend (List<T>) -> Unit,
        deleteFromDatabase: suspend (List<T>) -> Unit,
        onFirebaseLoading: (List<T>) -> Unit,
        onDatabaseLoading: (List<T>) -> Unit
    ): Unit = coroutineScope {
        val firebaseJob = asyncOnIO { loadFromFirebase() }
        val databaseJob = asyncOnIO { loadFromDatabase() }
        val databaseData = databaseJob.await()
        onDatabaseLoading(databaseData)
        val firebaseData = firebaseJob.await()
        if (firebaseData != databaseData || firebaseData.isEmpty()) {
            onFirebaseLoading(firebaseData)
            val firebaseSet = firebaseData.toSet()
            val databaseSet = databaseData.toSet()
            val saveData = firebaseSet.minus(databaseSet)
            val deleteData = databaseSet.minus(firebaseSet)
            saveToDatabase(saveData.toList())
            deleteFromDatabase(deleteData.toList())
        }
    }

    suspend fun <T : BaseModel> loadAllDataWithoutSave(
        loadFromFirebase: suspend () -> List<T>,
        loadFromDatabase: suspend () -> List<T>,
        onFirebaseLoading: (List<T>) -> Unit,
        onDatabaseLoading: (List<T>) -> Unit
    ): Unit = coroutineScope {
        val firebaseJob = asyncOnIO { loadFromFirebase() }
        val databaseJob = asyncOnIO { loadFromDatabase() }
        val databaseData = databaseJob.await()
        onDatabaseLoading(databaseData)
        val firebaseData = firebaseJob.await()
        if (firebaseData != databaseData || firebaseData.isEmpty()) {
            onFirebaseLoading(firebaseData)
        }
    }

    suspend fun <T : BaseModel> addData(
        data: T,
        addToFirebase: suspend (T) -> String,
        addToDatabase: suspend (T) -> Unit
    ): String = coroutineScope {
        val id = addToFirebase(data)
        data.id = id
        addToDatabase(data)
        return@coroutineScope id
    }

    suspend fun addData(
        addToFirebase: suspend () -> Unit,
        addToDatabase: suspend () -> Unit
    ): Unit = coroutineScope {
        val firebaseJob = launchOnIO { addToFirebase() }
        val databaseJob = launchOnIO { addToDatabase() }
        databaseJob.join()
        firebaseJob.join()
    }

    suspend fun <T : BaseModel> updateData(
        data: T,
        updateInFirebase: suspend (T) -> Unit,
        updateInDatabase: suspend (T) -> Unit,
    ): Unit = coroutineScope {
        val firebaseJob = launchOnIO { updateInFirebase(data) }
        val databaseJob = launchOnIO { updateInDatabase(data) }
        databaseJob.join()
        firebaseJob.join()
    }

    suspend fun deleteData(
        deleteInFirebase: suspend () -> Unit,
        deleteInDatabase: suspend () -> Unit,
    ): Unit = coroutineScope {
        val firebaseJob = launchOnIO { deleteInFirebase() }
        val databaseJob = launchOnIO { deleteInDatabase() }
        databaseJob.join()
        firebaseJob.join()
    }
}