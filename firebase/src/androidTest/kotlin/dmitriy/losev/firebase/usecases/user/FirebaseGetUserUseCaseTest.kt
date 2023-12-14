package dmitriy.losev.firebase.usecases.user

import androidx.test.ext.junit.runners.AndroidJUnit4
import dmitriy.losev.firebase.core.usecases.BaseUserUseCaseTest
import dmitriy.losev.firebase.core.exception.UserNotAuthorizationException
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

@RunWith(AndroidJUnit4::class)
class FirebaseGetUserUseCaseTest: BaseUserUseCaseTest() {

    private val firebaseGetUserUseCase by inject<FirebaseGetUserUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testGetNotNullUserWithException(): Unit = runBlocking {

        val actualResult = firebaseGetUserUseCase.getUserWithException()

        assertEquals(user, actualResult)
    }

    @Test
    fun testGetNullableUserWithException(): Unit = runBlocking {

        logOut()

        assertFailsWith(exceptionClass = UserNotAuthorizationException::class) {
            firebaseGetUserUseCase.getUserWithException()
        }
    }

    @Test
    fun testGetNotNullUserWithoutException(): Unit = runBlocking {

        val actualResult = firebaseGetUserUseCase.getUserWithoutException()

        assertEquals(user, actualResult)
    }

    @Test
    fun testGetNullableUserWithoutException(): Unit = runBlocking {

        logOut()

        val actualResult = firebaseGetUserUseCase.getUserWithoutException()

        assertNull(actualResult)
    }
}