package dmitriy.losev.firebase.data.dto

import dmitriy.losev.core.models.types.DayOfWeek
import dmitriy.losev.core.models.types.PeriodType
import java.time.ZoneId

data class PeriodDTO(
    val id: String? = null,
    val lessonId: String? = null,
    val type: String = PeriodType.WEEK.name,
    val dayOfWeek: String = DayOfWeek.MONDAY.name,
    val firstDateTime: Long = 0,
    val zoneId: String = ZoneId.systemDefault().id
)