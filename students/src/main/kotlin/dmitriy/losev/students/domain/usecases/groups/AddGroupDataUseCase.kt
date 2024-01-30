package dmitriy.losev.students.domain.usecases.groups

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Group
import dmitriy.losev.database.domain.usecases.groups.DatabaseAddGroupUseCase
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseAddGroupUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class AddGroupDataUseCase(
    private val firebaseAddGroupUseCase: FirebaseAddGroupUseCase,
    private val databaseAddGroupUseCase: DatabaseAddGroupUseCase
): StudentsBaseUseCase(), DatabaseLoader {

    suspend fun addGroup(group: Group): String = addData(
        data = group,
        addToFirebase = { gr -> firebaseAddGroupUseCase.addGroup(gr) },
        addToDatabase = { gr -> databaseAddGroupUseCase.addGroup(gr) }
    )
}