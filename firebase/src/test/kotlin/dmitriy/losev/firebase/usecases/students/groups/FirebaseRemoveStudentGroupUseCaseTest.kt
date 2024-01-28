package dmitriy.losev.firebase.usecases.students.groups

import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseRemoveStudentGroupUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemoveStudentGroupUseCaseTest {

    private val firebaseStudentGroupsRepository = mockk<FirebaseStudentGroupsRepository>(relaxed = true)

    private val firebaseRemoveStudentGroupUseCase = FirebaseRemoveStudentGroupUseCase(firebaseStudentGroupsRepository)

    @Test
    fun testRemoveGroup(): Unit = runBlocking {

        firebaseRemoveStudentGroupUseCase.removeGroup(STUDENT_ID, GROUP_ID)

        coVerify { firebaseStudentGroupsRepository.removeGroup(STUDENT_ID, GROUP_ID) }
    }

    companion object {
        private const val STUDENT_ID = "student_id"
        private const val GROUP_ID = "group_id"
    }
}