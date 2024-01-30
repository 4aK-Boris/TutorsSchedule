package dmitriy.losev.database.domain.usecases.groups.students

import dmitriy.losev.core.models.Student
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.groups.GroupStudentsRepository

class DatabaseGetGroupStudentsUseCase(private val groupStudentsRepository: GroupStudentsRepository) : DatabaseBaseUseCase() {

    suspend fun getGroupStudents(groupId: String): List<Student> {
        return groupStudentsRepository.getGroupStudents(groupId)
    }
}