package dmitriy.losev.students.domain.converters

import dmitriy.losev.core.models.SimpleStudent
import dmitriy.losev.core.models.Student
import dmitriy.losev.core.models.types.StudentType
import dmitriy.losev.students.domain.models.ActiveStudent

class ActiveStudentConverter {

    fun map(value: SimpleStudent): ActiveStudent {
        return ActiveStudent(id = value.id, name = value.name, isNew = value.studentType == StudentType.NEW)
    }

    fun map(value: Student): ActiveStudent {
        return ActiveStudent(id = value.id, name = value.name, isNew = value.studentType == StudentType.NEW)
    }
}