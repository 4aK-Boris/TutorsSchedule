package dmitriy.losev.database.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dmitriy.losev.database.data.entity.ContactEntity

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addContact(contact: ContactEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateContact(contact: ContactEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveContact(contact: ContactEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveContacts(contacts: List<ContactEntity>)

    @Delete
    suspend fun deleteContact(contact: ContactEntity)

    @Delete
    suspend fun deleteContacts(contacts: List<ContactEntity>)

    @Query("SELECT * FROM contacts WHERE student_id = :studentId AND contact_id = :contactId")
    suspend fun getContact(studentId: String, contactId: String): ContactEntity?

    @Query("SELECT * FROM contacts WHERE student_id = :studentId")
    suspend fun getContacts(studentId: String): List<ContactEntity>
}