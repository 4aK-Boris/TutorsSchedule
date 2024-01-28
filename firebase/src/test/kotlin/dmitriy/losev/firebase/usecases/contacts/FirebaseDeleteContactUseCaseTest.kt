package dmitriy.losev.firebase.usecases.contacts

import dmitriy.losev.firebase.domain.repositories.FirebaseContactsRepository
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseDeleteContactUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseDeleteContactUseCaseTest {

    private val firebaseContactsRepository = mockk<FirebaseContactsRepository>(relaxed = true)

    private val firebaseDeleteContactUseCase = FirebaseDeleteContactUseCase(firebaseContactsRepository)

    @Test
    fun testDeleteContact(): Unit = runBlocking {

        firebaseDeleteContactUseCase.deleteContact(STUDENT_ID, CONTACT_ID)

        coVerify { firebaseContactsRepository.deleteContact(STUDENT_ID, CONTACT_ID) }
    }

    companion object {
        private const val CONTACT_ID = "contact_id"
        private const val STUDENT_ID = "student_id"
    }
}