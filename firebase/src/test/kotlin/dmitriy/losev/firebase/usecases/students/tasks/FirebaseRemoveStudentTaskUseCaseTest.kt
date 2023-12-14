package dmitriy.losev.firebase.usecases.students.tasks

import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentTasksRepository
import dmitriy.losev.firebase.domain.usecases.students.tasks.FirebaseRemoveStudentTaskUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemoveStudentTaskUseCaseTest {

    private val firebaseStudentTasksRepository = mockk<FirebaseStudentTasksRepository>(relaxed = true)

    private val firebaseRemoveStudentTaskUseCase = FirebaseRemoveStudentTaskUseCase(firebaseStudentTasksRepository)

    @Test
    fun testRemoveTask(): Unit = runBlocking {

        firebaseRemoveStudentTaskUseCase.removeTask(STUDENT_ID, TASK_ID)

        coVerify { firebaseStudentTasksRepository.removeTask(STUDENT_ID, TASK_ID) }
    }

    companion object {
        private const val STUDENT_ID = "student_id"
        private const val TASK_ID = "task_id"
    }
}