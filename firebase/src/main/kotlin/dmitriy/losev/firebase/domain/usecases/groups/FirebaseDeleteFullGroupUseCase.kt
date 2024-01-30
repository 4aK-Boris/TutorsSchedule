package dmitriy.losev.firebase.domain.usecases.groups

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseGetGroupStudentIdsUseCase
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseRemoveStudentGroupUseCase

class FirebaseDeleteFullGroupUseCase(
    private val firebaseDeleteGroupUseCase: FirebaseDeleteGroupUseCase,
    private val firebaseDeleteStudentGroupUseCase: FirebaseRemoveStudentGroupUseCase,
    private val firebaseGetGroupStudentIdsUseCase: FirebaseGetGroupStudentIdsUseCase
) : FirebaseBaseUseCase() {

    suspend fun deleteFullGroup(groupId: String): Unit = launchFun(
        f1 = { firebaseDeleteGroupUseCase.deleteGroup(groupId) },
        f2 = { deleteGroupFromStudent(groupId) }
    )

    private suspend fun deleteGroupFromStudent(groupId: String) {
        firebaseGetGroupStudentIdsUseCase.getGroupStudentIds(groupId).forEach { studentId ->
            firebaseDeleteStudentGroupUseCase.removeGroup(studentId, groupId)
        }
    }
}