package dmitriy.losev.firebase.domain.usecases.periods.tasks

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodTasksRepository

class FirebaseRemoveAllPeriodTasksUseCase(private val firebasePeriodTasksRepository: FirebasePeriodTasksRepository) : FirebaseBaseUseCase() {

    suspend fun removeAllTasks(periodId: String) {
        firebasePeriodTasksRepository.removeAllTasks(periodId)
    }
}