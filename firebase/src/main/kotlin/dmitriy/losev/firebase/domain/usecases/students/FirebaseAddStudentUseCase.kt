package dmitriy.losev.firebase.domain.usecases.students

import dmitriy.losev.core.models.Student
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.StudentAddException
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseAddStudentUseCase(
    private val firebaseStudentsRepository: FirebaseStudentsRepository,
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase
) : FirebaseBaseUseCase() {

    suspend fun addStudent(student: Student): String {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        return firebaseStudentsRepository.addStudent(teacherId, student) ?: throw StudentAddException()
    }
}