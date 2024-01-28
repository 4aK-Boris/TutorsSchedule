package dmitriy.losev.firebase.usecases.user

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseUserUseCaseTest
import dmitriy.losev.firebase.core.EMAIl
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateEmailUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
@Ignore("don't work")
class FirebaseUpdateEmailUseCaseTest : BaseUserUseCaseTest() {

    private val firebaseUpdateEmailUseCase by inject<FirebaseUpdateEmailUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        returnEmail()
        logOut()
    }

    @Test
    fun testUpdateEmail(): Unit = runBlocking {

        assertEquals(email, EMAIl)

        firebaseUpdateEmailUseCase.updateEmail(NEW_EMAIL)

        assertEquals(email, NEW_EMAIL)
    }

    companion object {
        private const val NEW_EMAIL = "dmitriylosevxxx@gmail.com"
    }
}