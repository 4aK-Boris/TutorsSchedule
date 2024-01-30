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
    suspend fun addSubject(subject: SubjectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSubject(subject: SubjectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSubject(subject: SubjectEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSubjects(subjects: List<SubjectEntity>)

    @Delete
    suspend fun deleteSubject(subject: SubjectEntity)

    @Delete
    suspend fun deleteSubjects(subjects: List<SubjectEntity>)

    @Query("SELECT * FROM subjects WHERE subject_id = :subjectId")
    suspend fun getSubject(subjectId: String): SubjectEntity?

    @Query("SELECT * FROM subjects")
    suspend fun getSubjects(): List<SubjectEntity>
}