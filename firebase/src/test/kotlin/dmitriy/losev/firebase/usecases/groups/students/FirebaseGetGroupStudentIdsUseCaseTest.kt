package dmitriy.losev.firebase.usecases.groups.students

import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupStudentsRepository
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseGetGroupStudentIdsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FirebaseGetGroupStudentIdsUseCaseTest {

    private val firebaseGroupStudentsRepository = mockk<FirebaseGroupStudentsRepository>()

    private val firebaseGetGroupStudentIdsUseCase = FirebaseGetGroupStudentIdsUseCase(firebaseGroupStudentsRepository)

    @Test
    fun testGetAllStudents(): Unit = runBlocking {

        val students = listOf(STUDENT_ID, STUDENT_ID, STUDENT_ID)

        coEvery { firebaseGroupStudentsRepository.getStudents(GROUP_ID) } returns students

        val actualResult = firebaseGetGroupStudentIdsUseCase.getGroupStudentIds(GROUP_ID)

        coVerify { firebaseGroupStudentsRepository.getStudents(GROUP_ID) }

        assertContentEquals(students, actualResult)
    }

    companion object {

        private const val GROUP_ID = "group_id"
        private const val STUDENT_ID = "student_id"
    }
}