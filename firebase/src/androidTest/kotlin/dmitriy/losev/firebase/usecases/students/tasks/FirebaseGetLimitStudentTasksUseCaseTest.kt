package dmitriy.losev.firebase.usecases.students.tasks

import dmitriy.losev.firebase.core.usecases.students.BaseStudentTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.tasks.FirebaseGetLimitStudentTasksUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetLimitStudentTasksUseCaseTest: BaseStudentTasksUseCaseTest() {

    private val firebaseGetLimitStudentTasksUseCase by inject<FirebaseGetLimitStudentTasksUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteTasks()
        logOut()
    }

    @Test
    fun testGetLimitTasksWithEnoughTasks(): Unit = runBlocking {

        val count = 10
        val tasksCount = 20

        addTasks(count = tasksCount)

        val actualResult = firebaseGetLimitStudentTasksUseCase.getLimitTasks(STUDENT_ID, count)

        assertEquals(count, actualResult.size)
    }

    @Test
    fun testGetLimitTasksWithNotEnoughTasks(): Unit = runBlocking {

        val count = 10
        val tasksCount = 5

        addTasks(count = tasksCount)

        val actualResult = firebaseGetLimitStudentTasksUseCase.getLimitTasks(STUDENT_ID, count)

        assertEquals(tasksCount, actualResult.size)
    }
}