package dmitriy.losev.database.domain.usecases.students.contacts

import dmitriy.losev.core.models.Contact
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.students.StudentContactsRepository

class DatabaseGetStudentContactsUseCase(private val studentContactsRepository: StudentContactsRepository) : DatabaseBaseUseCase() {

    suspend fun getStudentContacts(studentId: String): List<Contact> {
        return studentContactsRepository.getStudentContacts(studentId)
    }
}