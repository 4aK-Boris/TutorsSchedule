package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.core.models.Contact
import dmitriy.losev.core.toNotNull
import dmitriy.losev.core.toNullable
import dmitriy.losev.firebase.data.dto.ContactDTO

class ContactMapper(private val nameMapper: NameMapper) {

    fun map(value: Contact): ContactDTO {
        return ContactDTO(
            id = value.id,
            firstName = value.firstName.toNullable(),
            lastName = value.lastName.toNullable(),
            patronymic = value.patronymic.toNullable(),
            phoneNumber = value.phoneNumber.toNullable()
        )
    }

    fun map(value: ContactDTO): Contact {
        return Contact(
            id = value.id.toNotNull(),
            name = nameMapper.getMiddleName(firstName = value.firstName, lastName = value.lastName, patronymic = value.patronymic),
            shortName = nameMapper.getShortName(firstName = value.firstName, lastName = value.lastName, patronymic = value.patronymic),
            firstName = value.firstName.toNotNull(),
            lastName = value.lastName.toNotNull(),
            patronymic = value.patronymic.toNotNull(),
            phoneNumber = value.phoneNumber.toNotNull()
        )
    }
}