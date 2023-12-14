package dmitriy.losev.firebase.usecases.user

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.domain.usecases.FirebaseUriUseCase
import dmitriy.losev.firebase.domain.usecases.storage.FirebaseUploadAvatarStorageUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateAvatarUseCase
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import io.mockk.verifySequence
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FirebaseUpdateAvatarUseCaseTest {

    private val user = mockk<FirebaseUser>(relaxed = true)
    private val imageUri = mockk<Uri>(relaxed = true)
    private val result = mockk<Void>()

    private val firebaseGetUserUseCase = mockk<FirebaseGetUserUseCase>()
    private val firebaseUriUseCase = mockk<FirebaseUriUseCase>()
    private val firebaseUploadAvatarStorageUseCase = mockk<FirebaseUploadAvatarStorageUseCase>()

    private val task = FirebaseTask(data = result)

    private val firebaseUpdateAvatarUseCase = FirebaseUpdateAvatarUseCase(firebaseGetUserUseCase, firebaseUriUseCase, firebaseUploadAvatarStorageUseCase)

    @BeforeEach
    fun setUp() {
        every { firebaseUriUseCase.convertUrlToUri(url = IMAGE_URL) } returns imageUri
        every { user.updateProfile(any()) } returns task
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testUpdateAvatarUrlWithoutUser(): Unit = runBlocking {

        coEvery { firebaseGetUserUseCase.getUserWithException() } returns user
        coEvery { firebaseUploadAvatarStorageUseCase.uploadAvatar(imageUri) } returns imageUri

        firebaseUpdateAvatarUseCase.updateAvatar(IMAGE_URL)

        coVerifySequence {
            firebaseGetUserUseCase.getUserWithException()
            firebaseUriUseCase.convertUrlToUri(url = IMAGE_URL)
            user.updateProfile(any())
        }
    }

    @Test
    fun testUpdateAvatarUrlWithUser(): Unit = runBlocking {

        coEvery { firebaseUploadAvatarStorageUseCase.uploadAvatar(imageUri) } returns imageUri

        firebaseUpdateAvatarUseCase.updateAvatar(user, IMAGE_URL)

        verifySequence {
            firebaseUriUseCase.convertUrlToUri(url = IMAGE_URL)
            user.updateProfile(any())
        }
    }

    @Test
    fun testUpdateAvatarUriWithoutUser(): Unit = runBlocking {

        coEvery { firebaseGetUserUseCase.getUserWithException() } returns user
        coEvery { firebaseUploadAvatarStorageUseCase.uploadAvatar(imageUri) } returns imageUri

        firebaseUpdateAvatarUseCase.updateAvatar(imageUri)

        coVerifySequence {
            firebaseGetUserUseCase.getUserWithException()
            user.updateProfile(any())
        }

        verify { firebaseUriUseCase.convertUrlToUri(url = any()) wasNot Called }
    }

    @Test
    fun testUpdateAvatarUriWithUser(): Unit = runBlocking {

        coEvery { firebaseUploadAvatarStorageUseCase.uploadAvatar(imageUri) } returns imageUri

        firebaseUpdateAvatarUseCase.updateAvatar(user, imageUri)

        verify { user.updateProfile(any()) }

        verify { firebaseUriUseCase.convertUrlToUri(url = any()) wasNot Called }
    }

    companion object {
        private const val IMAGE_URL = ""
    }
}