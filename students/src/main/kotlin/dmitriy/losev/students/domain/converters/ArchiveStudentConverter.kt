package dmitriy.losev.students.domain.converters

import dmitriy.losev.core.models.SimpleStudent
import dmitriy.losev.core.models.Student
import dmitriy.losev.students.domain.models.ArchiveStudent

class ArchiveStudentConverter {

    fun map(value: SimpleStudent): ArchiveStudent {
        return ArchiveStudent(id = value.id, name = value.name)
    }

    fun map(value: Student): ArchiveStudent {
        return ArchiveStudent(id = value.id, name = value.name)
    }
}