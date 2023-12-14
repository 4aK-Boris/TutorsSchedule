package dmitriy.losev.firebase.domain.usecases.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.NullableLessonException
import dmitriy.losev.firebase.domain.models.Lesson
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseGetLessonUseCase(
    private val firebaseLessonsRepository: FirebaseLessonsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun getLesson(lessonId: String): Lesson {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseLessonsRepository.getLesson(teacherId = user.uid, lessonId = lessonId) ?: throw NullableLessonException()
    }
}