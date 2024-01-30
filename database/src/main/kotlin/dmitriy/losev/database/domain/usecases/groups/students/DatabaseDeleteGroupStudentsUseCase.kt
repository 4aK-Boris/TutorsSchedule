package dmitriy.losev.database.domain.usecases.groups.students

import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.groups.GroupStudentsRepository

class DatabaseDeleteGroupStudentsUseCase(private val groupStudentsRepository: GroupStudentsRepository) : DatabaseBaseUseCase() {

    suspend fun deleteGroupStudents(groupId: String, studentIds: List<String>) {
        return groupStudentsRepository.deleteGroupStudents(groupId, studentIds)
    }
}