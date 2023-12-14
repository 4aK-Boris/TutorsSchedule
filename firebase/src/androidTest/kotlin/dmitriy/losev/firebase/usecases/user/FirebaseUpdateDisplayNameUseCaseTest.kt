package dmitriy.losev.firebase.usecases.user

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseUserUseCaseTest
import dmitriy.losev.firebase.core.DISPLAY_NAME
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateDisplayNameUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class FirebaseUpdateDisplayNameUseCaseTest : BaseUserUseCaseTest() {

    private val firebaseUpdateDisplayNameUseCase by inject<FirebaseUpdateDisplayNameUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        returnDisplayName()
        logOut()
    }

    @Test
    fun testUpdateDisplayName(): Unit = runBlocking {

        assertEquals(displayName, DISPLAY_NAME)

        firebaseUpdateDisplayNameUseCase.updateDisplayName(FIRST_NAME, LAST_NAME)

        assertEquals(displayName, NEW_DISPLAY_NAME)
    }

    companion object {
        private const val FIRST_NAME = "Ирина"
        private const val LAST_NAME = "Кирица"
        private const val NEW_DISPLAY_NAME = "$FIRST_NAME $LAST_NAME"
    }
}