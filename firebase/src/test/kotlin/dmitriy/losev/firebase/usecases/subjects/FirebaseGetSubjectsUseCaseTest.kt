package dmitriy.losev.firebase.usecases.subjects

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseGetSubjectsUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseGetSubjectsUseCaseTest {

    private val user = mockk<FirebaseUser>()

    private val firebaseSubjectsRepository = mockk<FirebaseSubjectsRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseGetSimpleSubjectsUseCase = FirebaseGetSubjectsUseCase(firebaseSubjectsRepository, firebaseGetUseCase)

    @Test
    fun testGetSubjects(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseGetSimpleSubjectsUseCase.getSubjects()

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseSubjectsRepository.getSubjects(TEACHER_ID)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
    }
}