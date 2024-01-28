package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.core.models.Subject
import dmitriy.losev.core.toNotNull
import dmitriy.losev.core.toNotNullString
import dmitriy.losev.core.toNullable
import dmitriy.losev.firebase.data.dto.SubjectDTO

class SubjectMapper {

    fun map(value: Subject): SubjectDTO {
        return SubjectDTO(
            id = value.id,
            name = value.name.toNullable(),
            price = value.price.toIntOrNull()
        )
    }

    fun map(value: SubjectDTO): Subject {
        return Subject(
            id = value.id.toNotNull(),
            name = value.name.toNotNull(),
            price = value.price.toNotNullString()
        )
    }
}