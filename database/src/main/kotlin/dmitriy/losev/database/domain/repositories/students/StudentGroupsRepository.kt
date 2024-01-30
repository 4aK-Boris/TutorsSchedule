package dmitriy.losev.database.domain.repositories.students

import dmitriy.losev.core.models.Group

interface StudentGroupsRepository {

    suspend fun getStudentGroups(studentId: String): List<Group>
}