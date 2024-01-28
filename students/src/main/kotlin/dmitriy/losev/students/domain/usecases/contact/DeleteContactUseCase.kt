package dmitriy.losev.students.domain.usecases.contact

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.database.domain.usecases.contacts.DatabaseDeleteContactUseCase
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseDeleteContactUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class DeleteContactUseCase(
    private val firebaseDeleteContactUseCase: FirebaseDeleteContactUseCase,
    private val databaseDeleteContactUseCase: DatabaseDeleteContactUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun deleteContact(studentId: String, contactId: String): Unit = deleteData(
        deleteInFirebase = { firebaseDeleteContactUseCase.deleteContact(studentId, contactId) },
        deleteInDatabase = { databaseDeleteContactUseCase.deleteContact(studentId, contactId) }
    )
}