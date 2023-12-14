package dmitriy.losev.firebase.usecases.students

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import dmitriy.losev.firebase.domain.usecases.students.FirebaseDeleteStudentUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseDeleteStudentUseCaseTest {

    private val user = mockk<FirebaseUser>()

    private val firebaseStudentsRepository = mockk<FirebaseStudentsRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseDeleteStudentUseCase = FirebaseDeleteStudentUseCase(firebaseStudentsRepository, firebaseGetUseCase)

    @Test
    fun testDeleteStudent(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseDeleteStudentUseCase.deleteStudent(STUDENT_ID)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseStudentsRepository.deleteStudent(TEACHER_ID, STUDENT_ID)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val STUDENT_ID = "student_id"
    }
}