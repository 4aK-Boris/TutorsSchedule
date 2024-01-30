package dmitriy.losev.students.domain.usecases.groups

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.database.domain.usecases.groups.students.DatabaseAddGroupStudentsUseCase
import dmitriy.losev.database.domain.usecases.groups.students.DatabaseDeleteGroupStudentsUseCase
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseAddGroupStudentsUseCase
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseDeleteGroupStudentsUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class AddGroupStudentsUseCase(
    private val firebaseAddGroupStudentsUseCase: FirebaseAddGroupStudentsUseCase,
    private val firebaseDeleteGroupStudentsUseCase: FirebaseDeleteGroupStudentsUseCase,
    private val databaseAddGroupStudentsUseCase: DatabaseAddGroupStudentsUseCase,
    private val databaseDeleteGroupStudentsUseCase: DatabaseDeleteGroupStudentsUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun addGroupStudents(groupId: String, oldStudentIds: List<String> = emptyList(), newStudentIds: List<String>) {
        val oldStudentIdsSet = oldStudentIds.toSet()
        val newStudentIdsSet = newStudentIds.toSet()
        val addStudentIds = newStudentIdsSet.minus(oldStudentIdsSet).toList()
        val deleteStudentIds = oldStudentIdsSet.minus(newStudentIdsSet).toList()
        addData(
            addToFirebase = {
                firebaseAddGroupStudentsUseCase.addGroupStudents(groupId, addStudentIds)
                firebaseDeleteGroupStudentsUseCase.deleteGroupStudents(groupId, deleteStudentIds)
            },
            addToDatabase = {
                databaseAddGroupStudentsUseCase.addGroupStudents(groupId, addStudentIds)
                databaseDeleteGroupStudentsUseCase.deleteGroupStudents(groupId, deleteStudentIds)
            }
        )
    }
}