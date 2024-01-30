package dmitriy.losev.students.domain.usecases.groups

import dmitriy.losev.core.models.Group
import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.domain.usecases.StudentsCheckNameUseCase

class CheckGroupUseCase(private val studentsCheckNameUseCase: StudentsCheckNameUseCase, ): StudentsBaseUseCase() {

    suspend fun checkGroup(group: Group) {
        studentsCheckNameUseCase.checkName(group.name)
    }
}