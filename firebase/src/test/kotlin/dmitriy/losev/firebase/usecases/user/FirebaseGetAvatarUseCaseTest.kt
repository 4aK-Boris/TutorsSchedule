package dmitriy.losev.firebase.usecases.user

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetAvatarUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class FirebaseGetAvatarUseCaseTest {

    private val user = mockk<FirebaseUser>()

    private val firebaseGetUserUseCase = mockk<FirebaseGetUserUseCase>()

    private val firebaseGetAvatarUseCase = FirebaseGetAvatarUseCase(firebaseGetUserUseCase)

    @ParameterizedTest
    @MethodSource("args")
    fun testGetAvatarUriWithoutUser(photoUri: Uri?): Unit = runBlocking {

        every { user.photoUrl } returns photoUri

        coEvery { firebaseGetUserUseCase.getUserWithException() } returns user

        val actualResult = firebaseGetAvatarUseCase.getAvatarUri()

        coVerify { firebaseGetUserUseCase.getUserWithException() }

        assertEquals(photoUri, actualResult)
    }

    @ParameterizedTest
    @MethodSource("args")
    fun testGetAvatarUriWithUser(photoUri: Uri?): Unit = runBlocking {

        every { user.photoUrl } returns photoUri

        val actualResult = firebaseGetAvatarUseCase.getAvatarUri(user)

        assertEquals(photoUri, actualResult)
    }

    companion object {

        @JvmStatic
        fun args() = listOf(Arguments.of(null), Arguments.of(Uri.EMPTY))
    }
}