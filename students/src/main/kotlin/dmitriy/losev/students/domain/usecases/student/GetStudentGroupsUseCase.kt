package dmitriy.losev.students.domain.usecases.student

import dmitriy.losev.core.models.Group
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseGetGroupUseCase
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseGetLimitStudentGroupsUseCase
import dmitriy.losev.students.core.GROUPS_LIMIT
import dmitriy.losev.students.core.StudentsBaseUseCase

class GetStudentGroupsUseCase(
    private val firebaseGetLimitStudentGroupsUseCase: FirebaseGetLimitStudentGroupsUseCase,
    private val firebaseGetGroupUseCase: FirebaseGetGroupUseCase
) : StudentsBaseUseCase() {

    suspend fun getStudentGroups(studentId: String): List<Group> {
        return firebaseGetLimitStudentGroupsUseCase.getLimitGroups(studentId = studentId, count = GROUPS_LIMIT).map { groupId ->
            firebaseGetGroupUseCase.getGroup(groupId)
        }
    }
}