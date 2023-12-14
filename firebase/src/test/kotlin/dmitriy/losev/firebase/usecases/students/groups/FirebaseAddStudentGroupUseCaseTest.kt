package dmitriy.losev.firebase.usecases.students.groups

import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseAddStudentGroupUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseAddStudentGroupUseCaseTest {

    private val firebaseStudentGroupsRepository = mockk<FirebaseStudentGroupsRepository>(relaxed = true)

    private val firebaseAddStudentGroupUseCase = FirebaseAddStudentGroupUseCase(firebaseStudentGroupsRepository)

    @Test
    fun testAddGroup(): Unit = runBlocking {

        firebaseAddStudentGroupUseCase.addGroup(STUDENT_ID, GROUP_ID)

        coVerify { firebaseStudentGroupsRepository.addGroup(STUDENT_ID, GROUP_ID) }
    }

    companion object {
        private const val STUDENT_ID = "student_id"
        private const val GROUP_ID = "group_id"
    }
}