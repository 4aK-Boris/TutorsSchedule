package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.core.models.types.LessonType
import dmitriy.losev.core.toNotNull
import dmitriy.losev.core.toNullable
import dmitriy.losev.firebase.data.dto.LessonDTO
import dmitriy.losev.firebase.domain.models.Lesson

class LessonMapper {

    fun map(value: Lesson): LessonDTO {
        return LessonDTO(
            id = value.id,
            type = value.type.name,
            studentOrGroupId = value.studentOrGroupId.toNullable(),
            subjectId = value.subjectId.toNullable(),
            price = value.price,
            duration = value.duration
        )
    }

    fun map(value: LessonDTO): Lesson {
        return Lesson(
            id = value.id,
            type = LessonType.valueOf(value.type),
            studentOrGroupId = value.studentOrGroupId.toNotNull(),
            subjectId = value.subjectId.toNotNull(),
            price = value.price,
            duration = value.duration
        )
    }
}