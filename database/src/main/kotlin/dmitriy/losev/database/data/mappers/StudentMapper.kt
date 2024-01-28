package dmitriy.losev.database.data.mappers

import dmitriy.losev.core.models.Student
import dmitriy.losev.core.models.types.StudentType
import dmitriy.losev.core.toNotNull
import dmitriy.losev.core.toNullable
import dmitriy.losev.database.data.entity.StudentEntity

class StudentMapper(private val nameMapper: NameMapper) {

    fun map(value: Student): StudentEntity {
        return StudentEntity(
            id = value.id,
            firstName = value.firstName.toNullable(),
            lastName = value.lastName.toNullable(),
            patronymic = value.patronymic.toNullable(),
            phoneNumber = value.phoneNumber.toNullable(),
            email = value.email.toNullable(),
            skype = value.skype.toNullable(),
            discord = value.discord.toNullable(),
            comment = value.comment.toNullable(),
            studentType = value.studentType.name
        )
    }

    fun map(value: StudentEntity?): Student? {
        return value?.let {
            Student(
                id = value.id,
                firstName = value.firstName.toNotNull(),
                lastName = value.lastName.toNotNull(),
                patronymic = value.patronymic.toNotNull(),
                name = nameMapper.getName(firstName = value.firstName, lastName = value.lastName, patronymic = value.patronymic),
                shortName = nameMapper.getShortName(firstName = value.firstName, lastName = value.lastName, patronymic = value.patronymic),
                phoneNumber = value.phoneNumber.toNotNull(),
                email = value.email.toNotNull(),
                skype = value.skype.toNotNull(),
                discord = value.discord.toNotNull(),
                comment = value.comment.toNotNull(),
                studentType = StudentType.valueOf(value = value.studentType)
            )
        }
    }
}