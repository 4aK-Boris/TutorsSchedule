package dmitriy.losev.database.domain.usecases.groups.students

import dmitriy.losev.core.models.StudentName
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseGetStudentNamesUseCase

class DatabaseGetGroupStudentNamesUseCase(
    private val databaseGetGroupStudentIdsUseCase: DatabaseGetGroupStudentIdsUseCase,
    private val databaseGetStudentNamesUseCase: DatabaseGetStudentNamesUseCase
): DatabaseBaseUseCase() {

    suspend fun getGroupStudentNames(groupId: String): List<StudentName> {
        val studentIds = databaseGetGroupStudentIdsUseCase.getGroupStudentIds(groupId)
        return databaseGetStudentNamesUseCase.getStudentNames(studentIds)
    }
}