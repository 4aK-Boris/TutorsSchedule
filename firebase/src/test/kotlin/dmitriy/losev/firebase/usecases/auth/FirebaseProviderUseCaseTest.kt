package dmitriy.losev.firebase.usecases.auth

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseProviderUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseProviderUseCaseTest {

    private val user = mockk<FirebaseUser>(relaxed = true)

    private val firebaseGetUserUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseProviderUseCase = FirebaseProviderUseCase(firebaseGetUserUseCase)

    @Test
    fun testGetProviderWithoutUser(): Unit = runBlocking {

        coEvery { firebaseGetUserUseCase.getUserWithException() } returns user

        firebaseProviderUseCase.getProvider()

        coVerifySequence {
            firebaseGetUserUseCase.getUserWithException()
            user.providerData
        }
    }

    @Test
    fun testGetProviderWithUser(): Unit = runBlocking {

        firebaseProviderUseCase.getProvider(user)

        verify { user.providerData }
    }
}