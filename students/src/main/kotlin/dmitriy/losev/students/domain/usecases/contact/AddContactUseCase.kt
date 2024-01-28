package dmitriy.losev.students.domain.usecases.contact

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Contact
import dmitriy.losev.core.trimAndFirstCharToUpperCase
import dmitriy.losev.database.domain.usecases.contacts.DatabaseAddContactUseCase
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseAddContactUseCase
import dmitriy.losev.students.core.EMPTY_STRING
import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.domain.usecases.StudentsCheckNameUseCase
import dmitriy.losev.students.domain.usecases.StudentsCheckPhoneNumberUseCase

class AddContactUseCase(
    private val studentsCheckPhoneNumberUseCase: StudentsCheckPhoneNumberUseCase,
    private val studentsCheckNameUseCase: StudentsCheckNameUseCase,
    private val firebaseAddContactUseCase: FirebaseAddContactUseCase,
    private val databaseAddContactUseCase: DatabaseAddContactUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun addContact(studentId: String, firstName: String, lastName: String, patronymic: String, phoneNumber: String) {
        addData(
            data = checkAndCreateContact(firstName, lastName, patronymic, phoneNumber),
            addToFirebase = { contact -> firebaseAddContactUseCase.addContact(studentId, contact) },
            addToDatabase = { contact -> databaseAddContactUseCase.addContact(studentId, contact) }
        )
    }

    private suspend fun checkAndCreateContact(firstName: String, lastName: String, patronymic: String, phoneNumber: String): Contact {
        studentsCheckPhoneNumberUseCase.checkPhoneNumber(phoneNumber)
        studentsCheckNameUseCase.checkFirstName(firstName)
        return Contact(
            id = EMPTY_STRING,
            name = EMPTY_STRING,
            shortName = EMPTY_STRING,
            firstName = firstName.trimAndFirstCharToUpperCase(),
            lastName = lastName.trimAndFirstCharToUpperCase(),
            patronymic = patronymic.trimAndFirstCharToUpperCase(),
            phoneNumber = phoneNumber.trim()
        )
    }
}