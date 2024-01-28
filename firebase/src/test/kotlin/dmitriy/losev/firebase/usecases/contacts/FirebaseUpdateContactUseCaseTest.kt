package dmitriy.losev.firebase.usecases.contacts

import dmitriy.losev.core.models.Contact
import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseUpdateContactUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseUpdateContactUseCaseTest {

    private val contact = mockk<Contact>()

    private val firebaseContactsRepository = mockk<FirebaseContactsRepository>(relaxed = true)

    private val firebaseUpdateContactUseCase = FirebaseUpdateContactUseCase(firebaseContactsRepository)

    @Test
    fun testUpdateContact(): Unit = runBlocking {

        firebaseUpdateContactUseCase.updateContact(STUDENT_ID, CONTACT_ID, contact)

        coVerify { firebaseContactsRepository.updateContact(STUDENT_ID, CONTACT_ID, contact)  }
    }

    companion object {
        private const val CONTACT_ID = "contact_id"
        private const val STUDENT_ID = "student_id"
    }
}