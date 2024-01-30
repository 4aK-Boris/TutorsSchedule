package dmitriy.losev.firebase.usecases.groups

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.exception.NullableGroupException
import dmitriy.losev.core.models.Group
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupsRepository
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseGetGroupUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FirebaseGetGroupUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val group = mockk<Group>()

    private val firebaseGroupsRepository = mockk<FirebaseGroupsRepository>()
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseGetGroupUseCase = FirebaseGetGroupUseCase(firebaseGroupsRepository, firebaseGetUseCase)

    @Test
    fun testGetNotNullGroup(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        coEvery { firebaseGroupsRepository.getGroup(teacherId = TEACHER_ID, groupId = GROUP_ID) } returns group
        every { user.uid } returns TEACHER_ID

        val actualResult = firebaseGetGroupUseCase.getGroup(GROUP_ID)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseGroupsRepository.getGroup(teacherId = TEACHER_ID, groupId = GROUP_ID)
        }

        assertEquals(group, actualResult)
    }

    @Test
    fun testGetNullableGroup(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        coEvery { firebaseGroupsRepository.getGroup(teacherId = TEACHER_ID, groupId = GROUP_ID) } returns null
        every { user.uid } returns TEACHER_ID

        assertFailsWith(exceptionClass = NullableGroupException::class) {
            firebaseGetGroupUseCase.getGroup(GROUP_ID)
        }

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseGroupsRepository.getGroup(teacherId = TEACHER_ID, groupId = GROUP_ID)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val GROUP_ID = "group_id"
    }
}