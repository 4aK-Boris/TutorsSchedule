package dmitriy.losev.firebase.domain.usecases.students.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentLessonsRepository

class FirebaseAddStudentLessonUseCase(private val firebaseStudentLessonsRepository: FirebaseStudentLessonsRepository) : FirebaseBaseUseCase() {

    suspend fun addLesson(studentId: String, lessonId: String) {
        firebaseStudentLessonsRepository.addLesson(studentId, lessonId)
    }
}