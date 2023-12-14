package dmitriy.losev.firebase.usecases.contacts

import dmitriy.losev.firebase.domain.models.Contact
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseAddContactUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FirebaseAddContactUseCaseTest {

    private val contact = mockk<Contact>()

    private val firebaseContactsRepository = mockk<FirebaseContactsRepository>()

    private val firebaseAddContactUseCase = FirebaseAddContactUseCase(firebaseContactsRepository)

    @Test
    fun testAddContact(): Unit = runBlocking {

        coEvery { firebaseContactsRepository.addContact(STUDENT_ID, contact) } returns KEY

        val actualResult = firebaseAddContactUseCase.addContact(STUDENT_ID, contact)

        coVerify { firebaseContactsRepository.addContact(STUDENT_ID, contact) }

        assertEquals(KEY, actualResult)
    }

    companion object {
        private const val KEY = "key"
        private const val STUDENT_ID = "student_id"
    }
}