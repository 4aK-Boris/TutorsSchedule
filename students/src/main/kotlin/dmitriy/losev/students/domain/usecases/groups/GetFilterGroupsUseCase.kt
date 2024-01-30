package dmitriy.losev.students.domain.usecases.groups

import dmitriy.losev.core.models.Group
import dmitriy.losev.students.core.StudentsBaseUseCase

class GetFilterGroupsUseCase : StudentsBaseUseCase() {

    fun getFilterGroups(filterString: String, groups: List<Group>): List<Group> {
        return groups.filter { group -> group.name.contains(other = filterString, ignoreCase = true) }
    }
}