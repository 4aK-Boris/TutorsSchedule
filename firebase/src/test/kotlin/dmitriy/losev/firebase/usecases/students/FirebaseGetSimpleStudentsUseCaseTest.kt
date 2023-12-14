package dmitriy.losev.firebase.usecases.students

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import dmitriy.losev.firebase.domain.usecases.students.FirebaseGetSimpleStudentsUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseGetSimpleStudentsUseCaseTest {

    private val user = mockk<FirebaseUser>()

    private val firebaseStudentsRepository = mockk<FirebaseStudentsRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseGetSimpleStudentsUseCase = FirebaseGetSimpleStudentsUseCase(firebaseStudentsRepository, firebaseGetUseCase)

    @Test
    fun testGetSimpleStudents(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseGetSimpleStudentsUseCase.getSimpleStudents()

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseStudentsRepository.getSimpleStudents(TEACHER_ID)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
    }
}