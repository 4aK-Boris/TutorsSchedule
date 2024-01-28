package dmitriy.losev.firebase.usecases.contacts

import dmitriy.losev.core.models.Contact
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseGetContactsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FirebaseGetContactsUseCaseTest {

    private val contact = mockk<Contact>()

    private val firebaseContactsRepository = mockk<FirebaseContactsRepository>()

    private val firebaseGetContactsUseCase = FirebaseGetContactsUseCase(firebaseContactsRepository)

    @Test
    fun testGetContacts(): Unit = runBlocking {

        val expectedContacts = listOf(contact, contact, contact)

        coEvery { firebaseContactsRepository.getContacts(STUDENT_ID) } returns expectedContacts

        val actualResult = firebaseGetContactsUseCase.getContacts(STUDENT_ID)

        coVerify { firebaseContactsRepository.getContacts(STUDENT_ID) }

        assertContentEquals(expectedContacts, actualResult)
    }

    companion object {
        private const val STUDENT_ID = "student_id"
    }
}