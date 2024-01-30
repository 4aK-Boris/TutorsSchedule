package dmitriy.losev.database.domain.usecases.students.groups

import dmitriy.losev.core.models.Group
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.students.StudentGroupsRepository

class DatabaseGetStudentGroupsUseCase(private val studentGroupsRepository: StudentGroupsRepository) : DatabaseBaseUseCase() {

    suspend fun getStudentGroups(studentId: String): List<Group> {
        return studentGroupsRepository.getStudentGroups(studentId)
    }
}