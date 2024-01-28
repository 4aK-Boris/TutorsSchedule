package dmitriy.losev.firebase.usecases.subjects

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.models.Subject
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseAddSubjectUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseAddSubjectUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val subject = mockk<Subject>()

    private val firebaseSubjectsRepository = mockk<FirebaseSubjectsRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseAddSubjectUseCase = FirebaseAddSubjectUseCase(firebaseSubjectsRepository, firebaseGetUseCase)

    @Test
    fun testAddSubject(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseAddSubjectUseCase.addSubject(subject)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseSubjectsRepository.addSubject(TEACHER_ID, any())
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
    }
}