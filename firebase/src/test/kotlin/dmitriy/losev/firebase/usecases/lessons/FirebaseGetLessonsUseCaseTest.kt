package dmitriy.losev.firebase.usecases.lessons

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonsRepository
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseGetLessonsUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseGetLessonsUseCaseTest {

    private val user = mockk<FirebaseUser>()

    private val firebaseLessonsRepository = mockk<FirebaseLessonsRepository>(relaxed = true)
    private val firebaseGetUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseGetSimpleLessonsUseCase = FirebaseGetLessonsUseCase(firebaseLessonsRepository, firebaseGetUseCase)

    @Test
    fun testGetLessons(): Unit = runBlocking {

        coEvery { firebaseGetUseCase.getUserWithException() } returns user
        every { user.uid } returns TEACHER_ID

        firebaseGetSimpleLessonsUseCase.getLessons()

        coVerifySequence {
            firebaseGetUseCase.getUserWithException()
            firebaseLessonsRepository.getLessons(TEACHER_ID)
        }
    }

    companion object {
        private const val TEACHER_ID = "teacher_id"
    }
}