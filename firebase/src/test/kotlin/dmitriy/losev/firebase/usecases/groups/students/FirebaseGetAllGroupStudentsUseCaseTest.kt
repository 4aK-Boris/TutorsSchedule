package dmitriy.losev.firebase.usecases.groups.students

import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupStudentsRepository
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseGetAllGroupStudentsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FirebaseGetAllGroupStudentsUseCaseTest {

    private val firebaseGroupStudentsRepository = mockk<FirebaseGroupStudentsRepository>()

    private val firebaseGetAllGroupStudentsUseCase = FirebaseGetAllGroupStudentsUseCase(firebaseGroupStudentsRepository)

    @Test
    fun testGetAllStudents(): Unit = runBlocking {

        val students = listOf(STUDENT_ID, STUDENT_ID, STUDENT_ID)

        coEvery { firebaseGroupStudentsRepository.getAllStudents(GROUP_ID) } returns students

        val actualResult = firebaseGetAllGroupStudentsUseCase.getAllStudents(GROUP_ID)

        coVerify { firebaseGroupStudentsRepository.getAllStudents(GROUP_ID) }

        assertContentEquals(students, actualResult)
    }

    companion object {

        private const val GROUP_ID = "group_id"
        private const val STUDENT_ID = "student_id"
    }
}