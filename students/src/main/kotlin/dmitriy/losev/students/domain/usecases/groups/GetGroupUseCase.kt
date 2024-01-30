package dmitriy.losev.students.domain.usecases.groups

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Group
import dmitriy.losev.database.domain.usecases.groups.DatabaseGetGroupUseCase
import dmitriy.losev.database.domain.usecases.groups.DatabaseSaveGroupUseCase
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseGetGroupUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class GetGroupUseCase(
    private val firebaseGetGroupUseCase: FirebaseGetGroupUseCase,
    private val databaseGetGroupUseCase: DatabaseGetGroupUseCase,
    private val databaseSaveGroupUseCase: DatabaseSaveGroupUseCase
): StudentsBaseUseCase(), DatabaseLoader {

    suspend fun getGroup(groupId: String, onFirebaseLoading: (Group) -> Unit, onDatabaseLoading: (Group?) -> Unit) {
        loadData(
            loadFromFirebase = { firebaseGetGroupUseCase.getGroup(groupId) },
            loadFromDatabase = { databaseGetGroupUseCase.getGroup(groupId) },
            saveToDatabase = databaseSaveGroupUseCase::saveGroup,
            onFirebaseLoading = onFirebaseLoading,
            onDatabaseLoading = onDatabaseLoading
        )
    }
}