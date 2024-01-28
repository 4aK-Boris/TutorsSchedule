package dmitriy.losev.firebase.domain.models

import dmitriy.losev.core.models.types.DayOfWeek
import dmitriy.losev.core.models.types.PeriodType
import java.time.ZonedDateTime

data class Period(
    val id: String?,
    val lessonId: String,
    val type: PeriodType,
    val dayOfWeek: DayOfWeek,
    val firstDateTime: ZonedDateTime
)
