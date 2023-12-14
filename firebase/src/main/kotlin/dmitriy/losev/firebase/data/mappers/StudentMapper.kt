package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.firebase.core.toNotNull
import dmitriy.losev.firebase.core.toNullable
import dmitriy.losev.firebase.data.dto.StudentDTO
import dmitriy.losev.firebase.domain.models.Student
import dmitriy.losev.firebase.domain.models.types.StudentType

class StudentMapper(private val nameMapper: NameMapper) {

    fun map(value: Student): StudentDTO {
        return StudentDTO(
            id = value.id,
            firstName = value.firstName.toNullable(),
            lastName = value.lastName.toNullable(),
            nickName = value.nickName.toNullable(),
            phoneNumber = value.phoneNumber.toNullable(),
            email = value.email.toNullable(),
            skype = value.skype.toNullable(),
            discord = value.discord.toNullable(),
            address = value.address.toNullable(),
            comment = value.comment.toNullable(),
            studentType = value.studentType.name
        )
    }

    fun map(value: StudentDTO): Student {
        return Student(
            id = value.id,
            firstName = value.firstName.toNotNull(),
            lastName = value.lastName.toNotNull(),
            nickName = value.nickName.toNotNull(),
            name = nameMapper.map(firstName = value.firstName, lastName = value.lastName, nickName = value.nickName),
            phoneNumber = value.phoneNumber.toNotNull(),
            email = value.email.toNotNull(),
            skype = value.skype.toNotNull(),
            discord = value.discord.toNotNull(),
            address = value.address.toNotNull(),
            comment = value.comment.toNotNull(),
            studentType = StudentType.valueOf(value = value.studentType)
        )
    }
}