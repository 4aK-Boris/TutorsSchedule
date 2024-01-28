package dmitriy.losev.firebase.domain.usecases.periods

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.usecases.periods.tasks.FirebaseGetAllPeriodTasksUseCase
import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseDeleteTaskUseCase

class FirebaseDeleteFullPeriodUseCase(
    private val firebaseGetAllPeriodTasksUseCase: FirebaseGetAllPeriodTasksUseCase,
    private val firebaseDeletePeriodUseCase: FirebaseDeletePeriodUseCase,
    private val firebaseDeleteTaskUseCase: FirebaseDeleteTaskUseCase
): FirebaseBaseUseCase() {

    suspend fun deleteFullPeriod(periodId: String) {
        launchFun(
            f1 = { deleteAllTasks(periodId) },
            f2 = { firebaseDeletePeriodUseCase.deletePeriod(periodId) }
        )
    }

    private suspend fun deleteAllTasks(periodId: String) {
        firebaseGetAllPeriodTasksUseCase.getAllTasks(periodId).forEach {
            taskId -> firebaseDeleteTaskUseCase.deleteTask(taskId)
        }
    }
}