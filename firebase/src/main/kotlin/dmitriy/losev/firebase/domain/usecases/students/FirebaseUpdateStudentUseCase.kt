package dmitriy.losev.firebase.domain.usecases.students

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.Student
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseUpdateStudentUseCase(
    private val firebaseStudentsRepository: FirebaseStudentsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun updateStudent(studentId: String, student: Student) {
        val user = firebaseGetUserUseCase.getUserWithException()
        firebaseStudentsRepository.updateStudent(teacherUId = user.uid, studentId = studentId, student = student)
    }
}