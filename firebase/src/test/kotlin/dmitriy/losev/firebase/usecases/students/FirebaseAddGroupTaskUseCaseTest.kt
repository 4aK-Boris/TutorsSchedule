package dmitriy.losev.firebase.usecases.students

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.models.Student
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import dmitriy.losev.firebase.domain.usecases.students.FirebaseAddStudentUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseAddGroupTaskUseCaseTest {

    private val user = mockk<FirebaseUser>()
    private val student = mockk<Student>()

    private val firebaseStudentsRepository = mockk<FirebaseStudentsRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseAddStudentUseCase = FirebaseAddStudentUseCase(firebaseStudentsRepository, firebaseGetUseCase)

    @Test
    fun testAddStudent(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseAddStudentUseCase.addStudent(student)

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseStudentsRepository.addStudent(TEACHER_ID, any())
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
    }
}