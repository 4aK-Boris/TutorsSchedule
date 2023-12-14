package dmitriy.losev.firebase.domain.usecases.periods.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodTasksRepository

class FirebaseGetAllPeriodTasksUseCase(private val firebasePeriodTasksRepository: FirebasePeriodTasksRepository) : FirebaseBaseUseCase() {

    suspend fun getAllTasks(periodId: String): List<String> {
        return firebasePeriodTasksRepository.getAllTasks(periodId)
    }
}