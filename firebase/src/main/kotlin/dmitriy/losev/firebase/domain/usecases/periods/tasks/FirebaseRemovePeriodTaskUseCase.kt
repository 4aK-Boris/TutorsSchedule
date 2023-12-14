package dmitriy.losev.firebase.domain.usecases.periods.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodTasksRepository

class FirebaseRemovePeriodTaskUseCase(private val firebasePeriodTasksRepository: FirebasePeriodTasksRepository) : FirebaseBaseUseCase() {

    suspend fun removeTask(periodId: String, taskId: String) {
        firebasePeriodTasksRepository.removeTask(periodId, taskId)
    }
}