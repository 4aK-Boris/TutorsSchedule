package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.firebase.data.dto.SimpleStudentDTO
import dmitriy.losev.firebase.domain.models.SimpleStudent
import dmitriy.losev.firebase.domain.models.types.StudentType

class SimpleStudentMapper(private val nameMapper: NameMapper) {

    fun map(value: SimpleStudentDTO): SimpleStudent? {
        return value.id?.let { id ->
            SimpleStudent(
                id = id,
                name = nameMapper.map(firstName = value.firstName, lastName = value.lastName, nickName = value.nickName),
                studentType = StudentType.valueOf(value = value.studentType)
            )
        }
    }
}