package dmitriy.losev.database.data.repositories

import dmitriy.losev.core.models.Contact
import dmitriy.losev.database.data.dao.ContactDao
import dmitriy.losev.database.data.mappers.ContactMapper
import dmitriy.losev.database.domain.repositories.ContactsRepository

class ContactsRepositoryImpl(
    private val contactDao: ContactDao,
    private val contactMapper: ContactMapper
): ContactsRepository {

    override suspend fun addContact(studentId: String, contact: Contact) {
        val contactEntity = contactMapper.map(studentId = studentId, value = contact)
        contactDao.addContact(contactEntity)
    }

    override suspend fun updateContact(studentId: String, contact: Contact) {
        val contactEntity = contactMapper.map(studentId = studentId, value = contact)
        contactDao.updateContact(contactEntity)
    }

    override suspend fun saveContact(studentId: String, contact: Contact) {
        val contactEntity = contactMapper.map(studentId = studentId, value = contact)
        contactDao.saveContact(contactEntity)
    }

    override suspend fun saveContacts(studentId: String, contacts: List<Contact>) {
        val contactEntities = contacts.map { contact -> contactMapper.map(studentId = studentId, value = contact) }
        contactDao.saveContacts(contactEntities)
    }

    override suspend fun deleteContact(studentId: String, contactId: String) {
        contactDao.getContact(studentId, contactId)?.let { contactEntity ->
            contactDao.deleteContact(contactEntity)
        }
    }

    override suspend fun deleteContacts(studentId: String) {
        val contactEntities = contactDao.getContacts(studentId)
        contactDao.deleteContacts(contactEntities)
    }

    override suspend fun getContact(studentId: String, contactId: String): Contact? {
        return contactMapper.map(value = contactDao.getContact(studentId, contactId))
    }

    override suspend fun getContacts(studentId: String): List<Contact> {
        return contactDao.getContacts(studentId).mapNotNull { contactEntity -> contactMapper.map(value = contactEntity) }
    }
}