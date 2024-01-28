package dmitriy.losev.firebase.usecases.subjects

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.models.Subject
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseUpdateSubjectUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseUpdateSubjectUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val subject = mockk<Subject>()

    private val firebaseSubjectsRepository = mockk<FirebaseSubjectsRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseUpdateSubjectUseCase = FirebaseUpdateSubjectUseCase(firebaseSubjectsRepository, firebaseGetUseCase)

    @Test
    fun testUpdateSubject(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseUpdateSubjectUseCase.updateSubject(SUBJECT_ID, subject)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseSubjectsRepository.updateSubject(TEACHER_ID, SUBJECT_ID, subject)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val SUBJECT_ID = "subject_id"
    }
}