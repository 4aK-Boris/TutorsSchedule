package dmitriy.losev.firebase.domain.usecases.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.LessonAddException
import dmitriy.losev.firebase.domain.models.Lesson
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseAddLessonUseCase(
    private val firebaseLessonsRepository: FirebaseLessonsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun addLesson(lesson: Lesson): String {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseLessonsRepository.addLesson(teacherId = user.uid, lesson = lesson) ?: throw LessonAddException()
    }
}