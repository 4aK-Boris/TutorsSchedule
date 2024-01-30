package dmitriy.losev.firebase.domain.usecases.groups.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupTasksRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseGetAllGroupTasksUseCase(
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase,
    private val firebaseGroupTasksRepository: FirebaseGroupTasksRepository
) : FirebaseBaseUseCase() {

    suspend fun getAllTasks(groupId: String): List<String> {
        val teacherId = firebaseGetUserIdUseCase.getUserIdWithException()
        return firebaseGroupTasksRepository.getTasks(teacherId, groupId)
    }
}