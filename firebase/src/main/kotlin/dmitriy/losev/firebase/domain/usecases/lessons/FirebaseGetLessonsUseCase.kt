package dmitriy.losev.firebase.domain.usecases.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.Lesson
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseGetLessonsUseCase(
    private val firebaseLessonsRepository: FirebaseLessonsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun getLessons(): List<Lesson> {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseLessonsRepository.getLessons(teacherId = user.uid)
    }
}