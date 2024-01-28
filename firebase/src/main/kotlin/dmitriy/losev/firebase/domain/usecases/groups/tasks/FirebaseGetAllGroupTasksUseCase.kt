package dmitriy.losev.firebase.domain.usecases.groups.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupTasksRepository

class FirebaseGetAllGroupTasksUseCase(private val firebaseGroupTasksRepository: FirebaseGroupTasksRepository) : FirebaseBaseUseCase() {

    suspend fun getAllTasks(groupId: String): List<String> {
        return firebaseGroupTasksRepository.getAllTasks(groupId)
    }
}