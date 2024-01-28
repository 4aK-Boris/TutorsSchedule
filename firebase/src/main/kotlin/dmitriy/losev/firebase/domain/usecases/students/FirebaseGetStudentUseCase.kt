package dmitriy.losev.firebase.domain.usecases.students

import dmitriy.losev.core.models.Student
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.NullableStudentException
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseGetStudentUseCase(
    private val firebaseStudentsRepository: FirebaseStudentsRepository,
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase
): FirebaseBaseUseCase() {

    suspend fun getStudent(studentId: String): Student {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        return firebaseStudentsRepository.getStudent(teacherId, studentId) ?: throw NullableStudentException()
    }
}