package dmitriy.losev.firebase.usecases.students.tasks

import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentTasksRepository
import dmitriy.losev.firebase.domain.usecases.students.tasks.FirebaseRemoveAllStudentTasksUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemoveAllStudentTasksUseCaseTest {

    private val firebaseStudentTasksRepository = mockk<FirebaseStudentTasksRepository>(relaxed = true)

    private val firebaseRemoveAllStudentTasksUseCase = FirebaseRemoveAllStudentTasksUseCase(firebaseStudentTasksRepository)

    @Test
    fun testRemoveAllTasks(): Unit = runBlocking {

        firebaseRemoveAllStudentTasksUseCase.removeAllTasks(STUDENT_ID)

        coVerify { firebaseStudentTasksRepository.removeAllTasks(STUDENT_ID) }
    }

    companion object {
        private const val STUDENT_ID = "student_id"
    }
}