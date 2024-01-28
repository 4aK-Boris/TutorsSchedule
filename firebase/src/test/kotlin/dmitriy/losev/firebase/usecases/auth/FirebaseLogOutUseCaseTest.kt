package dmitriy.losev.firebase.usecases.auth

import com.google.firebase.auth.FirebaseAuth
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseLogOutUseCase
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseLogOutUseCaseTest {

    private val auth = mockk<FirebaseAuth>(relaxed = true)

    private val firebaseLogOutUseCase = FirebaseLogOutUseCase(auth)

    @Test
    fun testLogOut(): Unit = runBlocking {

        firebaseLogOutUseCase.logOut()

        verify { auth.signOut() }
    }
}