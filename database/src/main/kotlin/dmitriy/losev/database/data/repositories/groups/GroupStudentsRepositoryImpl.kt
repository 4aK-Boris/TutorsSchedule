package dmitriy.losev.database.data.repositories.groups

import dmitriy.losev.core.models.Student
import dmitriy.losev.database.data.dao.GroupDao
import dmitriy.losev.database.data.dao.StudentGroupCrossRefDao
import dmitriy.losev.database.data.mappers.StudentGroupCrossRefMapper
import dmitriy.losev.database.data.mappers.StudentMapper
import dmitriy.losev.database.data.mappers.StudentNameMapper
import dmitriy.losev.database.domain.repositories.groups.GroupStudentsRepository

class GroupStudentsRepositoryImpl(
    private val groupDao: GroupDao,
    private val studentGroupCrossRefDao: StudentGroupCrossRefDao,
    private val studentMapper: StudentMapper,
    private val studentGroupCrossRefMapper: StudentGroupCrossRefMapper,
    private val studentNameMapper: StudentNameMapper
): GroupStudentsRepository {

    override suspend fun getGroupStudents(groupId: String): List<Student> {
        return groupDao.getGroupStudents(groupId)?.students?.mapNotNull { studentEntity -> studentMapper.map(value = studentEntity) } ?: emptyList()
    }

//    override suspend fun getGroupStudentNames(groupId: String): List<StudentName> {
//        return studentGroupCrossRefDao.getGroupStudentNames(groupId).map { studentNameDTO -> studentNameMapper.map(value = studentNameDTO) }
//    }

    override suspend fun getGroupStudentIds(groupId: String): List<String> {
        return studentGroupCrossRefDao.getGroupStudentIds(groupId)
    }

    override suspend fun addGroupStudent(groupId: String, studentId: String) {
        studentGroupCrossRefDao.addGroupStudent(studentGroupCrossRefMapper.map(groupId, studentId))
    }

    override suspend fun addGroupStudents(groupId: String, studentIds: List<String>) {
        studentGroupCrossRefDao.addGroupStudent(
            groupStudents = studentIds.map { studentId -> studentGroupCrossRefMapper.map(groupId, studentId) }
        )
    }

    override suspend fun saveGroupStudents(groupId: String, studentIds: List<String>) {
        studentGroupCrossRefDao.saveGroupStudents(
            groupStudents = studentIds.map { studentId -> studentGroupCrossRefMapper.map(groupId, studentId) }
        )
    }

    override suspend fun deleteGroupStudents(groupId: String, studentIds: List<String>) {
        studentGroupCrossRefDao.deleteGroupStudents(
            groupStudents = studentIds.map { studentId -> studentGroupCrossRefMapper.map(groupId, studentId) }
        )
    }
}