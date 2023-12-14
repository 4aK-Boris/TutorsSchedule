package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.firebase.core.toNotNull
import dmitriy.losev.firebase.core.toNullable
import dmitriy.losev.firebase.data.dto.SubjectDTO
import dmitriy.losev.firebase.domain.models.Subject

class SubjectMapper {

    fun map(value: Subject): SubjectDTO {
        return SubjectDTO(
            id = value.id,
            name = value.name.toNullable()
        )
    }

    fun map(value: SubjectDTO): Subject {
        return Subject(
            id = value.id,
            name = value.name.toNotNull()
        )
    }
}