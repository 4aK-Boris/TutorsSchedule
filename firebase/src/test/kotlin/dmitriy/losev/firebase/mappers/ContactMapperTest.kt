package dmitriy.losev.firebase.mappers

import dmitriy.losev.firebase.data.dto.ContactDTO
import dmitriy.losev.firebase.data.mappers.ContactMapper
import dmitriy.losev.core.models.Contact
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class ContactMapperTest {

    private val contact = mockk<Contact>()
    private val contactDTO = mockk<ContactDTO>()

    private val contactMapper = ContactMapper()

    @ParameterizedTest
    @MethodSource("straight")
    fun testMap(
        id: String?,
        name: String,
        phoneNumber: String,
        expectedId: String?,
        expectedName: String?,
        expectedPhoneNumber: String?
    ) {
        every { contact.id } returns id
        every { contact.name } returns name
        every { contact.phoneNumber } returns phoneNumber

        val actualResult = contactMapper.map(value = contact)

        assertEquals(expectedId, actualResult.id)
        assertEquals(expectedName, actualResult.firstName)
        assertEquals(expectedPhoneNumber, actualResult.phoneNumber)
    }

    @ParameterizedTest
    @MethodSource("reverse")
    fun testInverseMap(
        id: String?,
        name: String?,
        phoneNumber: String?,
        expectedId: String?,
        expectedName: String,
        expectedPhoneNumber: String
    ) {
        every { contactDTO.id } returns id
        every { contactDTO.firstName } returns name
        every { contactDTO.phoneNumber } returns phoneNumber

        val actualResult = contactMapper.map(value = contactDTO)

        assertEquals(expectedId, actualResult.id)
        assertEquals(expectedName, actualResult.name)
        assertEquals(expectedPhoneNumber, actualResult.phoneNumber)
    }

    companion object {
        
        private const val ID = "c324m2-384-302894392004 32"
        private const val NAME = "Юрий Степанович"
        private const val PHONE_NUMBER = "+79037805695"

        @JvmStatic
        fun straight() = listOf(
            Arguments.of(null, "", "", null, null, null),
            Arguments.of(null, NAME, "", null, NAME, null),
            Arguments.of(null, "", PHONE_NUMBER, null, null, PHONE_NUMBER),
            Arguments.of(null, NAME, PHONE_NUMBER, null, NAME, PHONE_NUMBER),
            Arguments.of(ID, "", "", ID, null, null),
            Arguments.of(ID, NAME, "", ID, NAME, null),
            Arguments.of(ID, "", PHONE_NUMBER, ID, null, PHONE_NUMBER),
            Arguments.of(ID, NAME, PHONE_NUMBER, ID, NAME, PHONE_NUMBER)
        )

        @JvmStatic
        fun reverse() = listOf(
            Arguments.of(null, null, null, null, "", ""),
            Arguments.of(null, NAME, null, null, NAME, ""),
            Arguments.of(null, null, PHONE_NUMBER, null, "", PHONE_NUMBER),
            Arguments.of(null, NAME, PHONE_NUMBER, null, NAME, PHONE_NUMBER),
            Arguments.of(ID, null, null, ID, "", ""),
            Arguments.of(ID, NAME, null, ID, NAME, ""),
            Arguments.of(ID, null, PHONE_NUMBER, ID, "", PHONE_NUMBER),
            Arguments.of(ID, NAME, PHONE_NUMBER, ID, NAME, PHONE_NUMBER)
        )
    }
}