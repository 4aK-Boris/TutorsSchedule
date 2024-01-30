package dmitriy.losev.database.data.repositories.students

import dmitriy.losev.core.models.Group
import dmitriy.losev.database.data.dao.StudentDao
import dmitriy.losev.database.data.mappers.GroupMapper
import dmitriy.losev.database.domain.repositories.students.StudentGroupsRepository

class StudentGroupsRepositoryImpl(
    private val studentDao: StudentDao,
    private val groupMapper: GroupMapper
): StudentGroupsRepository {

    override suspend fun getStudentGroups(studentId: String): List<Group> {
        return emptyList()//studentDao.getStudentGroups(studentId)?.groups?.mapNotNull { groupEntity -> groupMapper.map(value = groupEntity) } ?: emptyList()
    }
}