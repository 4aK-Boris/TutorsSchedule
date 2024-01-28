package dmitriy.losev.firebase.usecases.groups

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.models.Group
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupsRepository
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseAddGroupUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseAddGroupUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val group = mockk<Group>()

    private val firebaseGroupsRepository = mockk<FirebaseGroupsRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseAddGroupUseCase = FirebaseAddGroupUseCase(firebaseGroupsRepository, firebaseGetUseCase)

    @Test
    fun testAddGroup(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseAddGroupUseCase.addGroup(group)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseGroupsRepository.addGroup(TEACHER_ID, any())
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
    }
}