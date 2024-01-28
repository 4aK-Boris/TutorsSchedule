package dmitriy.losev.firebase.usecases.tasks

import dmitriy.losev.firebase.core.usecases.tasks.BaseTasksUseCaseTest
import dmitriy.losev.core.models.types.LessonStatus
import dmitriy.losev.core.models.types.PaidStatus
import dmitriy.losev.firebase.domain.usecases.tasks.FirebaseUpdateTaskUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import java.time.ZonedDateTime
import kotlin.test.assertTrue

class FirebaseUpdateTaskUseCaseTest : BaseTasksUseCaseTest() {

    private val firebaseUpdateTaskUseCase by inject<FirebaseUpdateTaskUseCase>()

    override suspend fun setUp() {
        logIn()
        addTask()
    }

    override suspend fun tearDown() {
        deleteTasks()
        logOut()
    }

    @Test
    fun testUpdateTask(): Unit = runBlocking {

        val hasTask = hasTask()

        assertTrue(hasTask)

        val newTask = task.copy(status = STATUS, paidStatus = PAID_STATUS, dateTime = ZonedDateTime.now())

        firebaseUpdateTaskUseCase.updateTask(TASK_ID, newTask)

        val actualResult = getTask()

        equalsTasks(newTask, actualResult)
    }

    companion object {
        private val STATUS = LessonStatus.CANCELLED
        private val PAID_STATUS = PaidStatus.PAID
    }
}