package dmitriy.losev.firebase.domain.usecases.groups.students

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseRemoveStudentGroupUseCase

class FirebaseDeleteGroupStudentsUseCase(
    private val firebaseRemoveGroupStudentUseCase: FirebaseRemoveGroupStudentUseCase,
    private val firebaseRemoveStudentGroupUseCase: FirebaseRemoveStudentGroupUseCase
) : FirebaseBaseUseCase() {

    suspend fun deleteGroupStudents(groupId: String, studentIds: List<String>) {
        studentIds.forEach { studentId ->
            firebaseRemoveGroupStudentUseCase.removeStudent(groupId, studentId)
            firebaseRemoveStudentGroupUseCase.removeGroup(studentId, groupId)
        }
    }
}