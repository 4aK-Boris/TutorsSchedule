package dmitriy.losev.firebase.domain.usecases.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.Lesson
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseUpdateLessonUseCase(
    private val firebaseLessonsRepository: FirebaseLessonsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun updateLesson(lessonId: String, lesson: Lesson) {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseLessonsRepository.updateLesson(teacherId = user.uid, lessonId = lessonId, lesson = lesson)
    }
}