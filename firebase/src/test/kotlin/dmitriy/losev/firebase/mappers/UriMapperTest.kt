package dmitriy.losev.firebase.mappers

import android.net.Uri
import dmitriy.losev.firebase.data.mappers.UriMapper
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UriMapperTest {

    private val uri = mockk<Uri>()

    private val uriMapper = UriMapper()

    @BeforeEach
    fun setUp() {
        mockkStatic(Uri::class)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testMap() {

        every { Uri.parse(URL) } returns uri

        val actualResult = uriMapper.map(URL)

        assertEquals(uri, actualResult)
    }

    companion object {
        private const val URL = "url"
    }
}