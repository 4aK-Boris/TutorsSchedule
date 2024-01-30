package dmitriy.losev.database.data.repositories.students

import dmitriy.losev.core.models.Contact
import dmitriy.losev.database.data.dao.ContactDao
import dmitriy.losev.database.data.mappers.ContactMapper
import dmitriy.losev.database.domain.repositories.students.StudentContactsRepository

class StudentContactsRepositoryImpl(
    private val contactDao: ContactDao,
    private val contactMapper: ContactMapper,
) : StudentContactsRepository {

    override suspend fun getStudentContacts(studentId: String): List<Contact> {
        return contactDao.getContacts(studentId).mapNotNull { contactEntity -> contactMapper.map(value = contactEntity) }
    }

    override suspend fun saveStudentContacts(studentId: String, contacts: List<Contact>) {
        val contactEntities = contacts.map { contact -> contactMapper.map(studentId = studentId, value = contact) }
        contactDao.saveContacts(contactEntities)
    }
}