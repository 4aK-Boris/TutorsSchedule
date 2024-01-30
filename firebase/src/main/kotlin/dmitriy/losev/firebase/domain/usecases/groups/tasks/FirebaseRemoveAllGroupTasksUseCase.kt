package dmitriy.losev.firebase.domain.usecases.groups.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupTasksRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseRemoveAllGroupTasksUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseGroupTasksRepository: FirebaseGroupTasksRepository
) : FirebaseBaseUseCase() {

    suspend fun removeAllTasks(groupId: String) {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        firebaseGroupTasksRepository.removeTasks(teacherId, groupId)
    }
}