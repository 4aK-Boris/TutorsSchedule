package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.firebase.data.dto.SimpleStudentDTO
import dmitriy.losev.core.models.SimpleStudent
import dmitriy.losev.core.models.types.StudentType

class SimpleStudentMapper(private val nameMapper: NameMapper) {

    fun map(value: SimpleStudentDTO): SimpleStudent? {
        return value.id?.let { id ->
            SimpleStudent(
                id = id,
                name = nameMapper.getName(firstName = value.firstName, lastName = value.lastName, patronymic = value.patronymic),
                studentType = StudentType.valueOf(value = value.studentType)
            )
        }
    }
}