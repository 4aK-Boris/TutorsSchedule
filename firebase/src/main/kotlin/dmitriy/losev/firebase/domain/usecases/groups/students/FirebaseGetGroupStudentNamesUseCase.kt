package dmitriy.losev.firebase.domain.usecases.groups.students

import dmitriy.losev.core.models.StudentName
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.usecases.students.FirebaseGetStudentNamesUseCase

class FirebaseGetGroupStudentNamesUseCase(
    private val firebaseGetGroupStudentIdsUseCase: FirebaseGetGroupStudentIdsUseCase,
    private val firebaseGetStudentNamesUseCase: FirebaseGetStudentNamesUseCase
) : FirebaseBaseUseCase() {

    suspend fun getGroupStudentIds(groupId: String): List<StudentName> {
        val studentIds = firebaseGetGroupStudentIdsUseCase.getGroupStudentIds(groupId)
        return firebaseGetStudentNamesUseCase.getStudentNames(studentIds)
    }
}