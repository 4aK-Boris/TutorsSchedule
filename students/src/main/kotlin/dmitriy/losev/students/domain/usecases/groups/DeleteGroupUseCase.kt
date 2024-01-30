package dmitriy.losev.students.domain.usecases.groups

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.database.domain.usecases.groups.DatabaseDeleteGroupUseCase
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseDeleteFullGroupUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class DeleteGroupUseCase(
    private val firebaseDeleteFullGroupUseCase: FirebaseDeleteFullGroupUseCase,
    private val databaseDeleteGroupUseCase: DatabaseDeleteGroupUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun deleteGroup(groupId: String): Unit = deleteData(
        deleteInFirebase = { firebaseDeleteFullGroupUseCase.deleteFullGroup(groupId = groupId) },
        deleteInDatabase = { databaseDeleteGroupUseCase.deleteGroup(groupId = groupId) }
    )
}