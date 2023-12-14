package dmitriy.losev.firebase.usecases.students.tasks

import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentTasksRepository
import dmitriy.losev.firebase.domain.usecases.students.tasks.FirebaseAddStudentTaskUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseAddStudentTaskUseCaseTest {

    private val firebaseStudentTasksRepository = mockk<FirebaseStudentTasksRepository>(relaxed = true)

    private val firebaseAddStudentTaskUseCase = FirebaseAddStudentTaskUseCase(firebaseStudentTasksRepository)

    @Test
    fun testAddTask(): Unit = runBlocking {

        firebaseAddStudentTaskUseCase.addTask(STUDENT_ID, TASK_ID)

        coVerify { firebaseStudentTasksRepository.addTask(STUDENT_ID, TASK_ID) }
    }

    companion object {
        private const val STUDENT_ID = "student_id"
        private const val TASK_ID = "task_id"
    }
}