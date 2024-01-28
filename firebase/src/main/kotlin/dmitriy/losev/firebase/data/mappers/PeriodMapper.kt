package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.core.models.types.DayOfWeek
import dmitriy.losev.core.models.types.PeriodType
import dmitriy.losev.core.toNotNull
import dmitriy.losev.core.toNullable
import dmitriy.losev.firebase.data.dto.PeriodDTO
import dmitriy.losev.firebase.domain.models.Period
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class PeriodMapper {

    fun map(value: Period): PeriodDTO {
        return PeriodDTO(
            id = value.id,
            lessonId = value.lessonId.toNullable(),
            type = value.type.name,
            dayOfWeek = value.dayOfWeek.name,
            firstDateTime = value.firstDateTime.toEpochSecond(),
            zoneId = value.firstDateTime.zone.id
        )
    }

    fun map(value: PeriodDTO): Period {
        val zone = ZoneId.of(value.zoneId)
        val instant = Instant.ofEpochSecond(value.firstDateTime)
        return Period(
            id = value.id,
            lessonId = value.lessonId.toNotNull(),
            type = PeriodType.valueOf(value.type),
            dayOfWeek = DayOfWeek.valueOf(value.dayOfWeek),
            firstDateTime = ZonedDateTime.ofInstant(instant, zone)
        )
    }
}