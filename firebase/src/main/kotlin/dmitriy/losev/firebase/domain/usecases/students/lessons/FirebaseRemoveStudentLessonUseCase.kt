package dmitriy.losev.firebase.domain.usecases.students.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentLessonsRepository

class FirebaseRemoveStudentLessonUseCase(private val firebaseStudentLessonsRepository: FirebaseStudentLessonsRepository) : FirebaseBaseUseCase() {

    suspend fun removeLesson(studentId: String, lessonId: String) {
        firebaseStudentLessonsRepository.removeLesson(studentId, lessonId)
    }
}