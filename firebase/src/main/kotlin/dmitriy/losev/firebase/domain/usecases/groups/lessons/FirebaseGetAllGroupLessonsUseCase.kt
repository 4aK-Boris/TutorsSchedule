package dmitriy.losev.firebase.domain.usecases.groups.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupLessonsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseGetAllGroupLessonsUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseGroupLessonsRepository: FirebaseGroupLessonsRepository
) : FirebaseBaseUseCase() {

    suspend fun getAllLessons(groupId: String): List<String> {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        return firebaseGroupLessonsRepository.getAllLessons(teacherId, groupId)
    }
}