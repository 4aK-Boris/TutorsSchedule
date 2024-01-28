package dmitriy.losev.students.domain.usecases.contact

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Contact
import dmitriy.losev.database.domain.usecases.contacts.DatabaseUpdateContactUseCase
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseUpdateContactUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.domain.usecases.StudentsCheckNameUseCase
import dmitriy.losev.students.domain.usecases.StudentsCheckPhoneNumberUseCase

class UpdateContactUseCase(
    private val studentsCheckPhoneNumberUseCase: StudentsCheckPhoneNumberUseCase,
    private val studentsCheckNameUseCase: StudentsCheckNameUseCase,
    private val firebaseUpdateContactUseCase: FirebaseUpdateContactUseCase,
    private val databaseUpdateContactUseCase: DatabaseUpdateContactUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun updateContact(studentId: String, contactId: String, contact: Contact) {
        checkContact(contact)
        updateData(
            data = contact,
            updateInFirebase = { data -> firebaseUpdateContactUseCase.updateContact(studentId, contactId, data) },
            updateInDatabase = { data -> databaseUpdateContactUseCase.updateContact(studentId, data) }
        )
    }

    private suspend fun checkContact(contact: Contact) {
        studentsCheckPhoneNumberUseCase.checkPhoneNumber(contact.phoneNumber)
        studentsCheckNameUseCase.checkFirstName(contact.firstName)
    }
}