package dmitriy.losev.firebase.usecases.students.groups

import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseGetAllStudentGroupsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FirebaseGetAllStudentGroupsUseCaseTest {

    private val firebaseStudentGroupsRepository = mockk<FirebaseStudentGroupsRepository>()

    private val firebaseGetAllStudentGroupsUseCase = FirebaseGetAllStudentGroupsUseCase(firebaseStudentGroupsRepository)

    @Test
    fun testGetAllGroups(): Unit = runBlocking {

        val groups = listOf(GROUP_ID, GROUP_ID, GROUP_ID)

        coEvery { firebaseStudentGroupsRepository.getAllGroups(STUDENT_ID) } returns groups

        val actualResult = firebaseGetAllStudentGroupsUseCase.getAllGroups(STUDENT_ID)

        coVerify { firebaseStudentGroupsRepository.getAllGroups(STUDENT_ID) }

        assertContentEquals(groups, actualResult)
    }

    companion object {

        private const val STUDENT_ID = "student_id"
        private const val GROUP_ID = "group_id"
    }
}