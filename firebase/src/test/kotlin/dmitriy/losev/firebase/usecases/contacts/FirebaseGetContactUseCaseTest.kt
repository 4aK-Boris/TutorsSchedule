package dmitriy.losev.firebase.usecases.contacts

import dmitriy.losev.core.models.Contact
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseGetContactUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FirebaseGetContactUseCaseTest {

    private val contact = mockk<Contact>()

    private val firebaseContactsRepository = mockk<FirebaseContactsRepository>()

    private val firebaseGetContactUseCase = FirebaseGetContactUseCase(firebaseContactsRepository)

    @Test
    fun testGetContact(): Unit = runBlocking {

        coEvery { firebaseContactsRepository.getContact(STUDENT_ID, CONTACT_ID) } returns contact

        val actualResult = firebaseGetContactUseCase.getContact(STUDENT_ID, CONTACT_ID)

        coVerify { firebaseContactsRepository.getContact(STUDENT_ID, CONTACT_ID)  }

        assertEquals(contact, actualResult)
    }

    companion object {
        private const val CONTACT_ID = "contact_id"
        private const val STUDENT_ID = "student_id"
    }
}