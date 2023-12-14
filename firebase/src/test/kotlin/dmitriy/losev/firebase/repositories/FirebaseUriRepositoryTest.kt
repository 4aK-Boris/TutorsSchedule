package dmitriy.losev.firebase.repositories

import android.net.Uri
import dmitriy.losev.firebase.data.mappers.UriMapper
import dmitriy.losev.firebase.data.repositories.FirebaseUriRepositoryImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FirebaseUriRepositoryTest {

    private val uri = mockk<Uri>()

    private val uriMapper = mockk<UriMapper>()

    private val firebaseUriRepository = FirebaseUriRepositoryImpl(uriMapper)

    @Test
    fun testConvertUrlToUri() {

        every { uriMapper.map(URL) } returns uri

        val actualResult = firebaseUriRepository.convertUrlToUri(URL)

        verify { uriMapper.map(URL) }

        assertEquals(uri, actualResult)
    }

    companion object {
        private const val URL = "url"
    }
}