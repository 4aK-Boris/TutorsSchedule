package dmitriy.losev.firebase.usecases.groups

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.models.Group
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupsRepository
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseUpdateGroupUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseUpdateGroupUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val group = mockk<Group>()

    private val firebaseGroupsRepository = mockk<FirebaseGroupsRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseUpdateGroupUseCase = FirebaseUpdateGroupUseCase(firebaseGroupsRepository, firebaseGetUseCase)

    @Test
    fun testUpdateGroup(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseUpdateGroupUseCase.updateGroup(GROUP_ID, group)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseGroupsRepository.updateGroup(TEACHER_ID, GROUP_ID, group)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val GROUP_ID = "group_id"
    }
}