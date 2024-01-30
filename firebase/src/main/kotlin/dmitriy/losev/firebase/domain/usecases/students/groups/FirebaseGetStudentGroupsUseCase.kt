package dmitriy.losev.firebase.domain.usecases.students.groups

import dmitriy.losev.core.models.Group
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseGetGroupUseCase

class FirebaseGetStudentGroupsUseCase(
    private val firebaseGetStudentGroupIdsUseCase: FirebaseGetStudentGroupIdsUseCase,
    private val firebaseGetGroupUseCase: FirebaseGetGroupUseCase
) : FirebaseBaseUseCase() {

    suspend fun getStudentGroups(studentId: String): List<Group> {
        val groupIds = firebaseGetStudentGroupIdsUseCase.getGroupIds(studentId)
        return groupIds.map { groupId -> firebaseGetGroupUseCase.getGroup(groupId) }
    }
}