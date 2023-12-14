package dmitriy.losev.firebase.usecases.students.tasks

import dmitriy.losev.firebase.core.usecases.students.BaseStudentTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.tasks.FirebaseRemoveStudentTaskUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveStudentTaskUseCaseTest: BaseStudentTasksUseCaseTest() {

    private val firebaseRemoveStudentTaskUseCase by inject<FirebaseRemoveStudentTaskUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteTasks()
        logOut()
    }

    @Test
    fun testRemoveTask(): Unit = runBlocking {

        addTask()

        var hasTask = hasTask()

        assertTrue(hasTask)

        firebaseRemoveStudentTaskUseCase.removeTask(STUDENT_ID, TASK_ID)

        hasTask = hasTask()

        assertFalse(hasTask)
    }
}