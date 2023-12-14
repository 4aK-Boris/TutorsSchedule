package dmitriy.losev.firebase.usecases.students

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.exception.NullableStudentException
import dmitriy.losev.firebase.domain.models.Student
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import dmitriy.losev.firebase.domain.usecases.students.FirebaseGetStudentUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FirebaseGetStudentUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val student = mockk<Student>()

    private val firebaseStudentsRepository = mockk<FirebaseStudentsRepository>()
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseGetStudentUseCase = FirebaseGetStudentUseCase(firebaseStudentsRepository, firebaseGetUseCase)

    @Test
    fun testGetNotNullStudent(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        coEvery { firebaseStudentsRepository.getStudent(teacherUId = TEACHER_ID, studentId = STUDENT_ID) } returns student
        every { user.uid } returns TEACHER_ID

        val actualResult = firebaseGetStudentUseCase.getStudent(STUDENT_ID)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseStudentsRepository.getStudent(teacherUId = TEACHER_ID, studentId = STUDENT_ID)
        }

        assertEquals(student, actualResult)
    }

    @Test
    fun testGetNullableStudent(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        coEvery { firebaseStudentsRepository.getStudent(teacherUId = TEACHER_ID, studentId = STUDENT_ID) } returns null
        every { user.uid } returns TEACHER_ID

        assertFailsWith(exceptionClass = NullableStudentException::class) {
            firebaseGetStudentUseCase.getStudent(STUDENT_ID)
        }

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseStudentsRepository.getStudent(teacherUId = TEACHER_ID, studentId = STUDENT_ID)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val STUDENT_ID = "student_id"
    }
}