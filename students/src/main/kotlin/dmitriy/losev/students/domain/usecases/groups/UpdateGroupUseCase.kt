package dmitriy.losev.students.domain.usecases.groups

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Group
import dmitriy.losev.database.domain.usecases.groups.DatabaseUpdateGroupUseCase
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseUpdateGroupUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class UpdateGroupUseCase(
    private val firebaseUpdateGroupUseCase: FirebaseUpdateGroupUseCase,
    private val databaseUpdateGroupUseCase: DatabaseUpdateGroupUseCase,
    private val checkGroupUseCase: CheckGroupUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun updateGroup(group: Group) {
        checkGroupUseCase.checkGroup(group)
        updateData(
            data = group,
            updateInFirebase = { firebaseUpdateGroupUseCase.updateGroup(groupId = group.id, group = group) },
            updateInDatabase = { databaseUpdateGroupUseCase.updateGroup(group = group) }
        )
    }
}