package dmitriy.losev.firebase.domain.usecases.students

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseDeleteStudentUseCase(
    private val firebaseStudentsRepository: FirebaseStudentsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun deleteStudent(studentId: String) {
        val user = firebaseGetUserUseCase.getUserWithException()
        firebaseStudentsRepository.deleteStudent(teacherUId = user.uid, studentId = studentId)
    }
}