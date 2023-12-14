package dmitriy.losev.firebase.usecases.students.tasks

import dmitriy.losev.firebase.core.usecases.students.BaseStudentTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.tasks.FirebaseAddStudentTaskUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseAddStudentTaskUseCaseTest: BaseStudentTasksUseCaseTest() {

    private val firebaseAddStudentTaskUseCase by inject<FirebaseAddStudentTaskUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteTasks()
        logOut()
    }

    @Test
    fun testAddTask(): Unit = runBlocking {

        firebaseAddStudentTaskUseCase.addTask(STUDENT_ID, TASK_ID)

        val hasTask = hasTask()

        assertTrue(hasTask)

        val actualResult = getTask()

        assertEquals(TASK_ID, actualResult)
    }
}