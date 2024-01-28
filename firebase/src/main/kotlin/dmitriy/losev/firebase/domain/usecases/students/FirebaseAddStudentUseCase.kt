package dmitriy.losev.firebase.domain.usecases.students

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.StudentAddException
import dmitriy.losev.firebase.domain.models.Student
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseAddStudentUseCase(
    private val firebaseStudentsRepository: FirebaseStudentsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
) : FirebaseBaseUseCase() {

    suspend fun addStudent(student: Student): String {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseStudentsRepository.addStudent(teacherUId = user.uid, student = student) ?: throw StudentAddException()
    }
}