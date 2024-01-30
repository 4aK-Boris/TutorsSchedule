package dmitriy.losev.database.domain.usecases.contacts

import dmitriy.losev.core.models.Contact
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.students.StudentContactsRepository

class DatabaseSaveContactsUseCase(private val studentContactsRepository: StudentContactsRepository): DatabaseBaseUseCase() {

    suspend fun saveContacts(studentId: String, contacts: List<Contact>) {
        studentContactsRepository.saveStudentContacts(studentId, contacts)
    }
}