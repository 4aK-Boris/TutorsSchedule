package dmitriy.losev.firebase.domain.usecases.students.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentLessonsRepository

class FirebaseRemoveAllStudentLessonsUseCase(private val firebaseStudentLessonsRepository: FirebaseStudentLessonsRepository) : FirebaseBaseUseCase() {

    suspend fun removeAllLessons(studentId: String) {
        firebaseStudentLessonsRepository.removeAllLessons(studentId)
    }
}