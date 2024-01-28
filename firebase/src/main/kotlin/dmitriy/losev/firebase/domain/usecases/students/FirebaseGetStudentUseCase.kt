package dmitriy.losev.firebase.domain.usecases.students

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.NullableStudentException
import dmitriy.losev.firebase.domain.models.Student
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseGetStudentUseCase(
    private val firebaseStudentsRepository: FirebaseStudentsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun getStudent(studentId: String): Student {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseStudentsRepository.getStudent(teacherUId = user.uid, studentId = studentId) ?: throw NullableStudentException()
    }
}