package dmitriy.losev.database.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dmitriy.losev.database.data.entity.GroupEntity
import dmitriy.losev.database.data.entity.StudentEntity
import dmitriy.losev.database.data.entity.StudentGroupCrossRefEntity

@Dao
interface StudentGroupCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGroupStudent(groupStudent: StudentGroupCrossRefEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGroupStudent(groupStudents: List<StudentGroupCrossRefEntity>)

    @Delete
    suspend fun deleteGroupStudents(groupStudents: List<StudentGroupCrossRefEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGroupStudents(groupStudents: List<StudentGroupCrossRefEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveStudentGroups(groupStudents: List<StudentGroupCrossRefEntity>)

    @Query("SELECT * FROM groups JOIN student_group_cross_ref ON groups.group_id = student_group_cross_ref.group_id WHERE student_id = :studentId")
    suspend fun getStudentGroups(studentId: String): List<GroupEntity>

    @Query("SELECT * FROM students JOIN student_group_cross_ref ON students.student_id = student_group_cross_ref.student_id WHERE group_id = :groupId")
    suspend fun getGroupStudents(groupId: String): List<StudentEntity>

//    @Query("SELECT student_id, first_name, last_name, patronymic FROM students JOIN student_group_cross_ref ON students.student_id = student_group_cross_ref.student_id WHERE student_group_cross_ref.group_id = :groupId")
//    suspend fun getGroupStudentNames(groupId: String): List<StudentNameDTO>

    @Query("SELECT student_id FROM student_group_cross_ref WHERE group_id = :groupId")
    suspend fun getGroupStudentIds(groupId: String): List<String>
}