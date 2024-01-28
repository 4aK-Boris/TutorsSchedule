package dmitriy.losev.students.domain.usecases.student

import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.domain.models.ActiveStudent
import dmitriy.losev.students.domain.models.ArchiveStudent

class GetFilterStudentsUseCase : StudentsBaseUseCase() {

    fun getActiveFilterStudents(filterString: String, students: List<ActiveStudent>): List<ActiveStudent> {
        return students.filter { student -> student.name.contains(other = filterString, ignoreCase = true) }
    }

    fun getArchiveFilterStudents(filterString: String, students: List<ArchiveStudent>): List<ArchiveStudent> {
        return students.filter { student -> student.name.contains(other = filterString, ignoreCase = true) }
    }
}