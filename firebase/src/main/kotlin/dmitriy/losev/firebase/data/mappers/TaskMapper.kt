package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.firebase.core.toNotNull
import dmitriy.losev.firebase.core.toNullable
import dmitriy.losev.firebase.data.dto.TaskDTO
import dmitriy.losev.firebase.domain.models.types.LessonStatus
import dmitriy.losev.firebase.domain.models.types.PaidStatus
import dmitriy.losev.firebase.domain.models.Task
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime


class TaskMapper {

    fun map(value: Task): TaskDTO {
        return TaskDTO(
            id = value.id,
            lessonId = value.lessonId.toNullable(),
            periodId = value.periodId.toNullable(),
            dateTime = value.dateTime.toEpochSecond(),
            zoneId = value.dateTime.zone.id,
            status = value.status.name,
            paidStatus = value.paidStatus.name
        )
    }

    fun map(value: TaskDTO): Task {
        val zone = ZoneId.of(value.zoneId)
        val instant = Instant.ofEpochSecond(value.dateTime)
        return Task(
            id = value.id,
            lessonId = value.lessonId.toNotNull(),
            periodId = value.periodId.toNotNull(),
            dateTime = ZonedDateTime.ofInstant(instant, zone),
            status = LessonStatus.valueOf(value.status),
            paidStatus = PaidStatus.valueOf(value.paidStatus)
        )
    }
}