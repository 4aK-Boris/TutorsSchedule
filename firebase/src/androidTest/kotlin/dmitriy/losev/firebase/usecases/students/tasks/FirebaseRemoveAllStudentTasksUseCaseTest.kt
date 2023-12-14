package dmitriy.losev.firebase.usecases.students.tasks

import dmitriy.losev.firebase.core.usecases.students.BaseStudentTasksUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.tasks.FirebaseRemoveAllStudentTasksUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveAllStudentTasksUseCaseTest: BaseStudentTasksUseCaseTest() {

    private val firebaseRemoveAllStudentTasksUseCase by inject<FirebaseRemoveAllStudentTasksUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteTasks()
        logOut()
    }

    @Test
    fun removeAllTasks(): Unit = runBlocking {

        val count = 10

        addTasks(count)

        var hasTasks = hasTasks()

        assertTrue(hasTasks)

        firebaseRemoveAllStudentTasksUseCase.removeAllTasks(STUDENT_ID)

        hasTasks = hasTasks()

        assertFalse(hasTasks)
    }
}