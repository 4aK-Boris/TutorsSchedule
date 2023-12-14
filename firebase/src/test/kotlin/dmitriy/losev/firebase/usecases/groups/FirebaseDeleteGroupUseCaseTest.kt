package dmitriy.losev.firebase.usecases.groups

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupsRepository
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseDeleteGroupUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseDeleteGroupUseCaseTest {

    private val user = mockk<FirebaseUser>()

    private val firebaseGroupsRepository = mockk<FirebaseGroupsRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseDeleteGroupUseCase = FirebaseDeleteGroupUseCase(firebaseGroupsRepository, firebaseGetUseCase)

    @Test
    fun testDeleteGroup(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseDeleteGroupUseCase.deleteGroup(GROUP_ID)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseGroupsRepository.deleteGroup(TEACHER_ID, GROUP_ID)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val GROUP_ID = "group_id"
    }
}