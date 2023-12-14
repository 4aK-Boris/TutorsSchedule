package dmitriy.losev.firebase.usecases.subjects

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.exception.NullableSubjectException
import dmitriy.losev.firebase.domain.models.Subject
import dmitriy.losev.firebase.domain.repositories.subjects.FirebaseSubjectsRepository
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseGetSubjectUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FirebaseGetSubjectUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val subject = mockk<Subject>()

    private val firebaseSubjectsRepository = mockk<FirebaseSubjectsRepository>()
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseGetSubjectUseCase = FirebaseGetSubjectUseCase(firebaseSubjectsRepository, firebaseGetUseCase)

    @Test
    fun testGetNotNullSubject(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        coEvery { firebaseSubjectsRepository.getSubject(teacherId = TEACHER_ID, subjectId = SUBJECT_ID) } returns subject
        every { user.uid } returns TEACHER_ID

        val actualResult = firebaseGetSubjectUseCase.getSubject(SUBJECT_ID)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseSubjectsRepository.getSubject(teacherId = TEACHER_ID, subjectId = SUBJECT_ID)
        }

        assertEquals(subject, actualResult)
    }

    @Test
    fun testGetNullableSubject(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        coEvery { firebaseSubjectsRepository.getSubject(teacherId = TEACHER_ID, subjectId = SUBJECT_ID) } returns null
        every { user.uid } returns TEACHER_ID

        assertFailsWith(exceptionClass = NullableSubjectException::class) {
            firebaseGetSubjectUseCase.getSubject(SUBJECT_ID)
        }

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseSubjectsRepository.getSubject(teacherId = TEACHER_ID, subjectId = SUBJECT_ID)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val SUBJECT_ID = "subject_id"
    }
}