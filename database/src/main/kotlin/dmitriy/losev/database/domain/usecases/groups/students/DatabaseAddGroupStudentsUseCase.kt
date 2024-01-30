package dmitriy.losev.database.domain.usecases.groups.students

import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.groups.GroupStudentsRepository

class DatabaseAddGroupStudentsUseCase(private val groupStudentsRepository: GroupStudentsRepository): DatabaseBaseUseCase() {

    suspend fun addGroupStudents(groupId: String, studentIds: List<String>) {
        groupStudentsRepository.addGroupStudents(groupId, studentIds)
    }
}