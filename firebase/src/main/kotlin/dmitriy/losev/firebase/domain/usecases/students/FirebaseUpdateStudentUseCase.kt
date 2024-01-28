package dmitriy.losev.firebase.domain.usecases.students

import dmitriy.losev.core.models.Student
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseUpdateStudentUseCase(
    private val firebaseStudentsRepository: FirebaseStudentsRepository,
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase
): FirebaseBaseUseCase() {

    suspend fun updateStudent(studentId: String, student: Student) {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        firebaseStudentsRepository.updateStudent(teacherId, studentId, student)
    }
}