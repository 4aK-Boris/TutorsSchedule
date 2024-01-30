package dmitriy.losev.students.domain.usecases.groups

import dmitriy.losev.core.EMPTY_STRING
import dmitriy.losev.core.models.Group
import dmitriy.losev.core.models.types.GroupType
import dmitriy.losev.students.core.StudentsBaseUseCase

class AddGroupUseCase(
    private val checkGroupUseCase: CheckGroupUseCase,
    private val addGroupDataUseCase: AddGroupDataUseCase,
    private val addGroupStudentsUseCase: AddGroupStudentsUseCase
): StudentsBaseUseCase() {

    suspend fun addGroup(name: String, studentIds: List<String>) {
        val group = createAndCheckGroup(name)
        val groupId = addGroupDataUseCase.addGroup(group)
        addGroupStudentsUseCase.addGroupStudents(groupId = groupId, newStudentIds = studentIds)
    }

    private suspend fun createAndCheckGroup(name: String): Group {
        val group = Group(id = EMPTY_STRING, name = name, groupType = GroupType.NEW)
        checkGroupUseCase.checkGroup(group)
        return group
    }
}