package dmitriy.losev.firebase.domain.usecases.students

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseDeleteStudentUseCase(
    private val firebaseStudentsRepository: FirebaseStudentsRepository,
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase
): FirebaseBaseUseCase() {

    suspend fun deleteStudent(studentId: String) {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        firebaseStudentsRepository.deleteStudent(teacherId, studentId)
    }
}