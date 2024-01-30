package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.core.models.StudentName
import dmitriy.losev.core.toNotNull
import dmitriy.losev.firebase.data.dto.StudentNameDTO

class StudentNameMapper(private val nameMapper: NameMapper) {

    fun map(value: StudentNameDTO): StudentName {
        return StudentName(
            id = value.id.toNotNull(),
            name = nameMapper.getName(firstName = value.firstName, lastName = value.lastName, patronymic = value.patronymic)
        )
    }
}