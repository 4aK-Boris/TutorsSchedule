package dmitriy.losev.firebase.domain.usecases.groups.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupTasksRepository

class FirebaseRemoveAllGroupTasksUseCase(private val firebaseGroupTasksRepository: FirebaseGroupTasksRepository) : FirebaseBaseUseCase() {

    suspend fun removeAllTasks(groupId: String) {
        firebaseGroupTasksRepository.removeAllTasks(groupId)
    }
}