package dmitriy.losev.firebase.usecases.students.groups

import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseGetLimitStudentGroupsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FirebaseGetLimitStudentGroupsUseCaseTest {

    private val firebaseStudentGroupsRepository = mockk<FirebaseStudentGroupsRepository>()

    private val firebaseGetLimitStudentGroupsUseCase = FirebaseGetLimitStudentGroupsUseCase(firebaseStudentGroupsRepository)

    @Test
    fun testGetAllGroups(): Unit = runBlocking {

        val count = 3

        val groups = listOf(GROUP_ID, GROUP_ID, GROUP_ID)

        coEvery { firebaseStudentGroupsRepository.getLimitGroups(STUDENT_ID, count) } returns groups

        val actualResult = firebaseGetLimitStudentGroupsUseCase.getLimitGroups(STUDENT_ID, count)

        coVerify { firebaseStudentGroupsRepository.getLimitGroups(STUDENT_ID, count) }

        assertContentEquals(groups, actualResult)
    }

    companion object {

        private const val STUDENT_ID = "student_id"
        private const val GROUP_ID = "group_id"
    }
}