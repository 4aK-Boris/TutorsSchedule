package dmitriy.losev.firebase.usecases.students.tasks

import dmitriy.losev.firebase.core.usecases.students.BaseStudentTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.tasks.FirebaseGetAllStudentTasksUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetAllStudentTasksUseCaseTest: BaseStudentTasksUseCaseTest() {

    private val firebaseGetAllStudentTasksUseCase by inject<FirebaseGetAllStudentTasksUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteTasks()
        logOut()
    }

    @Test
    fun testGetAllTasks(): Unit = runBlocking {

        val count = 10

        addTasks(count = count)

        val actualResult = firebaseGetAllStudentTasksUseCase.getAllTasks(STUDENT_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, taskId ->
            assertEquals("${TASK_ID}-$index", taskId)
        }
    }
}