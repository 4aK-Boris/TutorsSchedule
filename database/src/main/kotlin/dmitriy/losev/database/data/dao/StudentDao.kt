package dmitriy.losev.database.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dmitriy.losev.database.data.dto.StudentNameDTO
import dmitriy.losev.database.data.entity.StudentEntity

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStudent(student: StudentEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateStudent(student: StudentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveStudent(student: StudentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveStudents(students: List<StudentEntity>)

    @Delete
    suspend fun deleteStudent(student: StudentEntity)

    @Delete
    suspend fun deleteStudents(students: List<StudentEntity>)

    @Query("SELECT * FROM students WHERE student_id = :studentId")
    suspend fun getStudent(studentId: String): StudentEntity?

    @Query("SELECT * FROM students")
    suspend fun getStudents(): List<StudentEntity>

    @Query("SELECT student_id, first_name, last_name, patronymic FROM students WHERE student_type = 'active' OR student_type = 'new'")
    suspend fun getStudentNames(): List<StudentNameDTO>

    @Query("SELECT student_id, first_name, last_name, patronymic FROM students WHERE student_id in (:studentIds)")
    suspend fun getStudentNames(studentIds: List<String>): List<StudentNameDTO>
}