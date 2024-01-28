package dmitriy.losev.database.data.mappers

import dmitriy.losev.core.models.Contact
import dmitriy.losev.core.toNotNull
import dmitriy.losev.core.toNullable
import dmitriy.losev.database.data.entity.ContactEntity

class ContactMapper(private val nameMapper: NameMapper) {

    fun map(studentId: String, value: Contact): ContactEntity {
        return ContactEntity(
            id = value.id,
            studentId = studentId,
            firstName = value.firstName.toNullable(),
            lastName = value.lastName.toNullable(),
            patronymic = value.patronymic.toNullable(),
            phoneNumber = value.phoneNumber.toNullable()
        )
    }

    fun map(value: ContactEntity?): Contact? {
        return value?.let {
            Contact(
                id = value.id,
                name = nameMapper.getMiddleName(firstName = value.firstName, lastName = value.lastName, patronymic = value.patronymic),
                shortName = nameMapper.getShortName(firstName = value.firstName, lastName = value.lastName, patronymic = value.patronymic),
                firstName = value.firstName.toNotNull(),
                lastName = value.lastName.toNotNull(),
                patronymic = value.patronymic.toNotNull(),
                phoneNumber = value.phoneNumber.toNotNull()
            )
        }
    }
}