package dmitriy.losev.firebase.domain.usecases.groups.students

import dmitriy.losev.core.models.Student
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.usecases.students.FirebaseGetStudentUseCase

class FirebaseGetGroupStudentsUseCase(
    private val firebaseGetGroupStudentIdsUseCase: FirebaseGetGroupStudentIdsUseCase,
    private val firebaseGetStudentUseCase: FirebaseGetStudentUseCase
) : FirebaseBaseUseCase() {

    suspend fun getGroupStudents(groupId: String): List<Student> {
        val studentIds = firebaseGetGroupStudentIdsUseCase.getGroupStudentIds(groupId)
        return studentIds.map { studentId -> firebaseGetStudentUseCase.getStudent(studentId) }
    }
}