package dmitriy.losev.firebase.usecases.students.groups

import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseRemoveAllStudentGroupsUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemoveAllStudentGroupsUseCaseTest {

    private val firebaseStudentGroupsRepository = mockk<FirebaseStudentGroupsRepository>(relaxed = true)

    private val firebaseRemoveAllStudentGroupsUseCase = FirebaseRemoveAllStudentGroupsUseCase(firebaseStudentGroupsRepository)

    @Test
    fun testRemoveAllGroups(): Unit = runBlocking {

        firebaseRemoveAllStudentGroupsUseCase.removeAllGroups(STUDENT_ID)

        coVerify { firebaseStudentGroupsRepository.removeAllGroups(STUDENT_ID) }
    }

    companion object {
        private const val STUDENT_ID = "student_id"
    }
}