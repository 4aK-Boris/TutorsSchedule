package dmitriy.losev.database.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dmitriy.losev.database.data.entity.SubjectEntity

@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addSubject(subject: SubjectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateSubject(subject: SubjectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSubject(subject: SubjectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSubjects(subjects: List<SubjectEntity>)

    @Delete
    fun deleteSubject(subject: SubjectEntity)

    @Query("SELECT * FROM subjects WHERE id = :subjectId")
    fun getSubject(subjectId: String): SubjectEntity?

    @Query("SELECT * FROM subjects")
    fun getSubjects(): List<SubjectEntity>
}