package dmitriy.losev.firebase.domain.usecases.students

import dmitriy.losev.core.models.StudentName
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseGetStudentNamesUseCase(
    private val firebaseStudentsRepository: FirebaseStudentsRepository,
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase
): FirebaseBaseUseCase() {

    suspend fun getStudentNames(): List<StudentName> {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        return firebaseStudentsRepository.getStudentNames(teacherId)
    }

    suspend fun getStudentNames(studentIds: List<String>): List<StudentName> {
        val studentIdsSet = studentIds.toSet()
        return getStudentNames().filter { student -> student.id in studentIdsSet }
    }
}