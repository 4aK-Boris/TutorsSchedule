package dmitriy.losev.firebase.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateDisplayNameUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FirebaseUpdateDisplayNameUseCaseTest {

    private val user = mockk<FirebaseUser>(relaxed = true)
    private val result = mockk<Void>()

    private val firebaseGetUserUseCase = mockk<FirebaseGetUserUseCase>()

    private val task = FirebaseTask(data = result)

    private val firebaseUpdateDisplayNameUseCase = FirebaseUpdateDisplayNameUseCase(firebaseGetUserUseCase)

    @BeforeEach
    fun setUp() {
        every { user.updateProfile(any()) } returns task
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testUpdateDisplayNameWithoutUser(): Unit = runBlocking {

        coEvery { firebaseGetUserUseCase.getUserWithException() } returns user

        firebaseUpdateDisplayNameUseCase.updateDisplayName(FIRST_NAME, LAST_NAME)

        coVerifySequence {
            firebaseGetUserUseCase.getUserWithException()
            user.updateProfile(any())
        }
    }

    @Test
    fun testUpdateDisplayNameWithUser(): Unit = runBlocking {

        firebaseUpdateDisplayNameUseCase.updateDisplayName(user, FIRST_NAME, LAST_NAME)

        verify { user.updateProfile(any()) }
    }

    companion object {
        private const val FIRST_NAME = "Анастасия"
        private const val LAST_NAME = "Маркова"
    }
}