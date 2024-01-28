package dmitriy.losev.subjects.data.mappers

import dmitriy.losev.core.models.Subject
import dmitriy.losev.core.toNotNullString
import dmitriy.losev.subjects.data.dto.SubjectDTO

class SubjectMapper {

    fun map(value: Subject): SubjectDTO {
        return SubjectDTO(
            id = value.id,
            name = value.name,
            price = value.price.toIntOrNull()
        )
    }

    fun map(value: SubjectDTO): Subject {
        return Subject(
            id = value.id,
            name = value.name,
            price = value.price.toNotNullString()
        )
    }
}