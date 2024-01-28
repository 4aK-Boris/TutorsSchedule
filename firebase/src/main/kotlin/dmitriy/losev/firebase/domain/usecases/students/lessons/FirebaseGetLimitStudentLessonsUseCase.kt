package dmitriy.losev.firebase.domain.usecases.students.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentLessonsRepository

class FirebaseGetLimitStudentLessonsUseCase(private val firebaseStudentLessonsRepository: FirebaseStudentLessonsRepository) : FirebaseBaseUseCase() {

    suspend fun getLimitLessons(studentId: String, count: Int): List<String> {
        return firebaseStudentLessonsRepository.getLimitLessons(studentId, count)
    }
}