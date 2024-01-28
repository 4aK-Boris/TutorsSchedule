package dmitriy.losev.students.domain.usecases.tasks

import dmitriy.losev.firebase.domain.models.Task
import dmitriy.losev.firebase.domain.usecases.students.tasks.FirebaseGetLimitStudentTasksUseCase
import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseGetTaskUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.core.TASKS_LIMIT

class GetStudentTasksUseCase(
    private val firebaseGetLimitStudentTasksUseCase: FirebaseGetLimitStudentTasksUseCase,
    private val firebaseGetTaskUseCase: FirebaseGetTaskUseCase
) : StudentsBaseUseCase() {

    suspend fun getStudentTasks(studentId: String): List<Task> {
        return firebaseGetLimitStudentTasksUseCase.getLimitTasks(studentId = studentId, count = TASKS_LIMIT).map { taskId ->
            firebaseGetTaskUseCase.getTask(taskId)
        }
    }
}