package dmitriy.losev.firebase.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseLogOutUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseDeleteUserUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseDeleteUserUseCaseTest {

    private val user = mockk<FirebaseUser>(relaxed = true)

    private val firebaseGetUserUseCase = mockk<FirebaseGetUserUseCase>()
    private val firebaseLogOutUseCase = mockk<FirebaseLogOutUseCase>(relaxed = true)

    private val firebaseDeleteUserUseCase = FirebaseDeleteUserUseCase(firebaseGetUserUseCase, firebaseLogOutUseCase)

    @Test
    fun testDeleteAccountWithoutUser(): Unit = runBlocking {

        coEvery { firebaseGetUserUseCase.getUserWithException() } returns user

        firebaseDeleteUserUseCase.deleteAccount()

        coVerifySequence {
            firebaseGetUserUseCase.getUserWithException()
            user.delete()
            firebaseLogOutUseCase.logOut()
        }
    }

    @Test
    fun testDeleteAccountWithUser(): Unit = runBlocking {

        firebaseDeleteUserUseCase.deleteAccount(user)

        coVerifySequence {
            user.delete()
            firebaseLogOutUseCase.logOut()
        }
    }
}