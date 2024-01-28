package dmitriy.losev.database.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dmitriy.losev.database.data.entity.StudentEntity

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addStudent(student: StudentEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateStudent(student: StudentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveStudent(student: StudentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveStudents(students: List<StudentEntity>)

    @Delete
    fun deleteStudent(student: StudentEntity)

    @Query("SELECT * FROM students WHERE id = :studentId")
    fun getStudent(studentId: String): StudentEntity?

    @Query("SELECT * FROM students")
    fun getStudents(): List<StudentEntity>
}