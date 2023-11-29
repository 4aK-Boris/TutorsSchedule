package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.firebase.data.dto.StudentDTO
import dmitriy.losev.firebase.domain.models.Student
import dmitriy.losev.firebase.domain.models.StudentType

class StudentMapper {

    fun map(value: Student): StudentDTO {
        return StudentDTO(
            id = value.id,
            firstName = value.firstName.toNullable(),
            lastName = value.lastName.toNullable(),
            nickName = value.nickName.toNullable(),
            phoneNumber = value.phoneNumber.toNullable(),
            email = value.email.toNullable(),
            skype = value.skype.toNullable(),
            address = value.address.toNullable(),
            comment = value.comment.toNullable(),
            studentType = value.studentType.name
        )
    }

    fun map(value: StudentDTO): Student {
        return Student(
            id = value.id.toNotNull(),
            firstName = value.firstName.toNotNull(),
            lastName = value.lastName.toNotNull(),
            nickName = value.nickName.toNotNull(),
            name = getName(value.firstName, value.lastName, value.nickName),
            phoneNumber = value.phoneNumber.toNotNull(),
            email = value.email.toNotNull(),
            skype = value.skype.toNotNull(),
            address = value.address.toNotNull(),
            comment = value.comment.toNotNull(),
            studentType = StudentType.valueOf(value = value.studentType)
        )
    }

    private fun getName(firstName: String?, lastName: String?, nickName: String?): String {
        val firstNameNotNull = firstName.toNotNull()
        val lastNameNotNull = lastName.toNotNull()
        val nickNameNotNull = nickName.toNotNull()
        val name = "$firstNameNotNull $lastNameNotNull".trim()
        return if (nickNameNotNull.isNotBlank()) {
            "$name ($nickNameNotNull)".trim()
        } else {
            name
        }
    }

    private fun String.toNullable(): String? = this.ifBlank { null }

    private fun String?.toNotNull(): String = this ?: EMPTY_STRING

    companion object {
        private const val EMPTY_STRING = ""
    }
}