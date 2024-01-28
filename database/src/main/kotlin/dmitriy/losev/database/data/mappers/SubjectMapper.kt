package dmitriy.losev.database.data.mappers

import dmitriy.losev.core.models.Subject
import dmitriy.losev.core.toNotNullString
import dmitriy.losev.database.data.entity.SubjectEntity

class SubjectMapper {

    fun map(value: Subject): SubjectEntity {
        return SubjectEntity(
            id = value.id,
            name = value.name,
            price = value.price.toIntOrNull()
        )
    }

    fun map(value: SubjectEntity?): Subject? {
        return value?.let {
            Subject(
                id = value.id,
                name = value.name,
                price = value.price.toNotNullString()
            )
        }
    }
}