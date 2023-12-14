package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.firebase.core.toNotNull
import dmitriy.losev.firebase.core.toNullable
import dmitriy.losev.firebase.data.dto.ContactDTO
import dmitriy.losev.firebase.domain.models.Contact

class ContactMapper {

    fun map(value: Contact): ContactDTO {
        return ContactDTO(
            id = value.id,
            name = value.name.toNullable(),
            phoneNumber = value.phoneNumber.toNullable()
        )
    }

    fun map(value: ContactDTO): Contact {
        return Contact(
            id = value.id,
            name = value.name.toNotNull(),
            phoneNumber = value.phoneNumber.toNotNull()
        )
    }
}