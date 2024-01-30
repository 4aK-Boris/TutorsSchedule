package dmitriy.losev.database.domain.usecases.groups.students

import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.groups.GroupStudentsRepository

class DatabaseGetGroupStudentIdsUseCase(private val groupStudentsRepository: GroupStudentsRepository) : DatabaseBaseUseCase() {

    suspend fun getGroupStudentIds(groupId: String): List<String> {
        return groupStudentsRepository.getGroupStudentIds(groupId)
    }
}