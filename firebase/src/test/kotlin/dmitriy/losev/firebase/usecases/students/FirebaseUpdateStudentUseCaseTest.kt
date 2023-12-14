package dmitriy.losev.firebase.usecases.students

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.models.Student
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import dmitriy.losev.firebase.domain.usecases.students.FirebaseUpdateStudentUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseUpdateStudentUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val student = mockk<Student>()

    private val firebaseStudentsRepository = mockk<FirebaseStudentsRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseUpdateStudentUseCase = FirebaseUpdateStudentUseCase(firebaseStudentsRepository, firebaseGetUseCase)

    @Test
    fun testUpdateStudent(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseUpdateStudentUseCase.updateStudent(STUDENT_ID, student)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseStudentsRepository.updateStudent(TEACHER_ID, STUDENT_ID, student)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
        private const val STUDENT_ID = "student_id"
    }
}