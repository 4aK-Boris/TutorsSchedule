package dmitriy.losev.database.data.mappers

import dmitriy.losev.core.models.StudentName
import dmitriy.losev.database.data.dto.StudentNameDTO

class StudentNameMapper(private val nameMapper: NameMapper) {

    fun map(value: StudentNameDTO): StudentName {
        return StudentName(id = value.id, name = nameMapper.getName(firstName = value.firstName, lastName = value.lastName, patronymic = value.patronymic))
    }
}