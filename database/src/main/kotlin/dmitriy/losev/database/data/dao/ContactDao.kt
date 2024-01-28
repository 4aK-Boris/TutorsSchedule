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
    fun addContact(contact: ContactEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateContact(contact: ContactEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveContact(contact: ContactEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveContacts(contacts: List<ContactEntity>)

    @Delete
    fun deleteContact(contact: ContactEntity)

    @Delete
    fun deleteContacts(contacts: List<ContactEntity>)

    @Query("SELECT * FROM contacts WHERE student_id = :studentId AND id = :contactId")
    fun getContact(studentId: String, contactId: String): ContactEntity?

    @Query("SELECT * FROM contacts WHERE student_id = :studentId")
    fun getContacts(studentId: String): List<ContactEntity>
}