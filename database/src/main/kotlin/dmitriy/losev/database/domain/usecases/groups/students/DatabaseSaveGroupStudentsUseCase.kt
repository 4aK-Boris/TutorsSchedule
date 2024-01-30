package dmitriy.losev.database.domain.usecases.groups.students

import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.groups.GroupStudentsRepository

class DatabaseSaveGroupStudentsUseCase(private val groupStudentsRepository: GroupStudentsRepository) : DatabaseBaseUseCase() {

    suspend fun saveGroupStudents(groupId: String, studentIds: List<String>) {
        return groupStudentsRepository.saveGroupStudents(groupId, studentIds)
    }
}