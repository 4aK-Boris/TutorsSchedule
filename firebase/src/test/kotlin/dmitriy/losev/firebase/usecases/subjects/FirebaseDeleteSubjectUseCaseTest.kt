package dmitriy.losev.firebase.usecases.subjects

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseDeleteSubjectUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseDeleteSubjectUseCaseTest {

    private val user = mockk<FirebaseUser>()

    private val firebaseSubjectsRepository = mockk<FirebaseSubjectsRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseDeleteSubjectUseCase = FirebaseDeleteSubjectUseCase(firebaseSubjectsRepository, firebaseGetUseCase)

    @Test
    fun testDeleteSubject(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseDeleteSubjectUseCase.deleteSubject(SUBJECT_ID)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseSubjectsRepository.deleteSubject(TEACHER_ID, SUBJECT_ID)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val SUBJECT_ID = "subject_id"
    }
}