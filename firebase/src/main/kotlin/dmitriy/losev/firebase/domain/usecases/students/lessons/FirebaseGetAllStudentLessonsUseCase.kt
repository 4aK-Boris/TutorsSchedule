package dmitriy.losev.firebase.domain.usecases.students.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentLessonsRepository

class FirebaseGetAllStudentLessonsUseCase(private val firebaseStudentLessonsRepository: FirebaseStudentLessonsRepository) : FirebaseBaseUseCase() {

    suspend fun getAllLessons(studentId: String): List<String> {
        return firebaseStudentLessonsRepository.getAllLessons(studentId)
    }
}