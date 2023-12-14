package dmitriy.losev.firebase.repositories.contacts

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.CONTACTS
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.data.dto.ContactDTO
import dmitriy.losev.firebase.data.mappers.ContactMapper
import dmitriy.losev.firebase.data.repositories.FirebaseContactsRepositoryImpl
import dmitriy.losev.firebase.domain.models.Contact
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import io.mockk.verifySequence
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class FirebaseContactsRepositoryTest {

    private val contactsReference = mockk<DatabaseReference>()
    private val studentContactsReference = mockk<DatabaseReference>()
    private val contactReference = mockk<DatabaseReference>()
    private val contact = mockk<Contact>()
    private val contactDTO = mockk<ContactDTO>()
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()
    private val contactMapper = mockk<ContactMapper>()

    private val task = FirebaseTask(data = result)
    private val dataSnapshotTask = FirebaseTask(data = dataSnapshotResult)

    private val firebaseContactsRepository = FirebaseContactsRepositoryImpl(reference, contactMapper)

    @BeforeEach
    fun setUp() {
        every { reference.child(CONTACTS) } returns contactsReference
        every { contactsReference.child(STUDENT_ID) } returns studentContactsReference
        every { contactMapper.map(value = contact) } returns contactDTO
        every { contactMapper.map(value = contactDTO) } returns contact
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testAddContact(): Unit = runBlocking {

        every { studentContactsReference.push() } returns contactReference
        every { contactReference.key } returns KEY
        every { contactDTO.copy(id = KEY) } returns contactDTO
        every { contactReference.setValue(contactDTO) } returns task

        val actualResult = firebaseContactsRepository.addContact(STUDENT_ID, contact)

        verifySequence {
            contactMapper.map(value = contact)
            reference.child(CONTACTS)
            contactsReference.child(STUDENT_ID)
            studentContactsReference.push()
            contactReference.key
            contactReference.setValue(contactDTO)
            contactReference.key
        }

        assertEquals(KEY, actualResult)
    }

    @Test
    fun testGetNotNullContact(): Unit = runBlocking {

        every { studentContactsReference.child(CONTACT_ID) } returns contactReference
        every { contactReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.getValue(ContactDTO::class.java) } returns contactDTO

        val actualResult = firebaseContactsRepository.getContact(STUDENT_ID, CONTACT_ID)

        verifySequence {
            reference.child(CONTACTS)
            contactsReference.child(STUDENT_ID)
            studentContactsReference.child(CONTACT_ID)
            contactReference.get()
            dataSnapshotResult.getValue(ContactDTO::class.java)
            contactMapper.map(value = contactDTO)
        }

        assertEquals(contact, actualResult)
    }

    @Test
    fun testGetNullableContact(): Unit = runBlocking {

        every { studentContactsReference.child(CONTACT_ID) } returns contactReference
        every { contactReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.getValue(ContactDTO::class.java) } returns null

        val actualResult = firebaseContactsRepository.getContact(STUDENT_ID, CONTACT_ID)

        verifySequence {
            reference.child(CONTACTS)
            contactsReference.child(STUDENT_ID)
            studentContactsReference.child(CONTACT_ID)
            contactReference.get()
            dataSnapshotResult.getValue(ContactDTO::class.java)
        }

        verify { contactMapper.map(value = contactDTO) wasNot Called }

        assertNull(actualResult)
    }

    @Test
    fun testUpdateContact(): Unit = runBlocking {

        every { studentContactsReference.updateChildren(mapOf(CONTACT_ID to contactDTO)) } returns task

        firebaseContactsRepository.updateContact(STUDENT_ID, CONTACT_ID, contact)

        verifySequence {
            reference.child(CONTACTS)
            contactsReference.child(STUDENT_ID)
            contactMapper.map(value = contact)
            studentContactsReference.updateChildren(mapOf(CONTACT_ID to contactDTO))
        }
    }

    @Test
    fun testDeleteContact(): Unit = runBlocking {

        every { studentContactsReference.child(CONTACT_ID) } returns contactReference
        every { contactReference.removeValue() } returns task

        firebaseContactsRepository.deleteContact(STUDENT_ID, CONTACT_ID)

        verifySequence {
            reference.child(CONTACTS)
            contactsReference.child(STUDENT_ID)
            studentContactsReference.child(CONTACT_ID)
            contactReference.removeValue()
        }
    }

    @Test
    fun testGetContacts(): Unit = runBlocking {

        val listSnapshots = listOf(dataSnapshotResult, dataSnapshotResult, dataSnapshotResult)

        every { studentContactsReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.children } returns listSnapshots
        every { dataSnapshotResult.getValue(ContactDTO::class.java) } returns contactDTO

        val actualResult = firebaseContactsRepository.getContacts(STUDENT_ID)

        verifySequence {
            reference.child(CONTACTS)
            contactsReference.child(STUDENT_ID)
            studentContactsReference.get()
            dataSnapshotResult.children
            dataSnapshotResult.getValue(ContactDTO::class.java)
            contactMapper.map(value = contactDTO)
            dataSnapshotResult.getValue(ContactDTO::class.java)
            contactMapper.map(value = contactDTO)
            dataSnapshotResult.getValue(ContactDTO::class.java)
            contactMapper.map(value = contactDTO)
        }

        assertEquals(listSnapshots.size, actualResult.size)

        actualResult.forEach { student ->
            assertEquals(contact, student)
        }
    }

    companion object {
        private const val CONTACT_ID = "contact_id"
        private const val STUDENT_ID = "student_id"
        private const val KEY = "key"
    }
}