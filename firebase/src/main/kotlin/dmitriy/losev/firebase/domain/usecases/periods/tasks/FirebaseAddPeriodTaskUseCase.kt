package dmitriy.losev.firebase.domain.usecases.periods.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodTasksRepository

class FirebaseAddPeriodTaskUseCase(private val firebasePeriodTasksRepository: FirebasePeriodTasksRepository) : FirebaseBaseUseCase() {

    suspend fun addTask(periodId: String, taskId: String) {
        firebasePeriodTasksRepository.addTask(periodId, taskId)
    }
}