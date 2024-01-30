package dmitriy.losev.firebase.domain.usecases.groups.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupLessonsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseRemoveAllGroupLessonsUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseGroupLessonsRepository: FirebaseGroupLessonsRepository
) : FirebaseBaseUseCase() {

    suspend fun removeAllLessons(groupId: String) {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        firebaseGroupLessonsRepository.removeAllLessons(teacherId, groupId)
    }
}