package dmitriy.losev.firebase.domain.usecases.groups.students

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseAddStudentGroupUseCase

class FirebaseAddGroupStudentsUseCase(
    private val firebaseAddGroupStudentUseCase: FirebaseAddGroupStudentUseCase,
    private val firebaseAddStudentGroupUseCase: FirebaseAddStudentGroupUseCase
) : FirebaseBaseUseCase() {

    suspend fun addGroupStudents(groupId: String, studentIds: List<String>) {
        studentIds.forEach { studentId ->
            firebaseAddGroupStudentUseCase.addStudent(groupId, studentId)
            firebaseAddStudentGroupUseCase.addGroup(studentId, groupId)
        }
    }
}