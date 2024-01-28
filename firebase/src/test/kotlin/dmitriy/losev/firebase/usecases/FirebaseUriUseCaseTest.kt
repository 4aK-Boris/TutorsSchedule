package dmitriy.losev.firebase.usecases

import android.net.Uri
import dmitriy.losev.firebase.domain.repositories.FirebaseUriRepository
import dmitriy.losev.firebase.domain.usecases.FirebaseUriUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FirebaseUriUseCaseTest {

    private val uri = mockk<Uri>()

    private val firebaseUriRepository = mockk<FirebaseUriRepository>()

    private val firebaseUriUseCase = FirebaseUriUseCase(firebaseUriRepository)

    @Test
    fun testConvertUrlToUri() {

        every { firebaseUriRepository.convertUrlToUri(URL) } returns uri

        val actualResult = firebaseUriUseCase.convertUrlToUri(URL)

        verify { firebaseUriRepository.convertUrlToUri(URL) }

        assertEquals(uri, actualResult)
    }

    companion object {
        private const val URL = "url"
    }
}