package dmitriy.losev.firebase.domain.usecases.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseDeleteLessonUseCase(
    private val firebaseLessonsRepository: FirebaseLessonsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
) : FirebaseBaseUseCase() {

    suspend fun deleteLesson(lessonId: String) {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseLessonsRepository.deleteLesson(teacherId = user.uid, lessonId = lessonId)
    }
}