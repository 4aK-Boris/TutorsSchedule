package dmitriy.losev.database.domain.repositories.groups

import dmitriy.losev.core.models.Student

interface GroupStudentsRepository {

    suspend fun getGroupStudents(groupId: String): List<Student>

    //suspend fun getGroupStudentNames(groupId: String): List<StudentName>

    suspend fun getGroupStudentIds(groupId: String): List<String>

    suspend fun addGroupStudent(groupId: String, studentId: String)

    suspend fun addGroupStudents(groupId: String, studentIds: List<String>)

    suspend fun saveGroupStudents(groupId: String, studentIds: List<String>)

    suspend fun deleteGroupStudents(groupId: String, studentIds: List<String>)
}