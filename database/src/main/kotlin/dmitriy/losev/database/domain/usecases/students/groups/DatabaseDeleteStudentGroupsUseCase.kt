package dmitriy.losev.database.domain.usecases.students.groups

import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.students.StudentGroupsRepository

class DatabaseDeleteStudentGroupsUseCase(private val studentGroupsRepository: StudentGroupsRepository) : DatabaseBaseUseCase() {

    suspend fun deleteStudentGroups(studentId: String) {
        //return studentGroupsRepository.deleteStudentGroups(studentId)
    }
}