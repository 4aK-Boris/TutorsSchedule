package dmitriy.losev.database.core

import androidx.room.Database
import androidx.room.RoomDatabase
import dmitriy.losev.database.data.dao.ContactDao
import dmitriy.losev.database.data.dao.StudentDao
import dmitriy.losev.database.data.dao.SubjectDao
import dmitriy.losev.database.data.entity.ContactEntity
import dmitriy.losev.database.data.entity.StudentEntity
import dmitriy.losev.database.data.entity.SubjectEntity

@Database(entities = [SubjectEntity::class, ContactEntity::class, StudentEntity::class], version = 1)
internal abstract class TeacherTaskDatabase : RoomDatabase() {

    abstract fun subjectDao(): SubjectDao

    abstract fun contactDao(): ContactDao

    abstract fun studentDao(): StudentDao
}