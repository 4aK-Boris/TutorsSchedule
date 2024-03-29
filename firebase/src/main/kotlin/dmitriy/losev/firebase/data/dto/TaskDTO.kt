package dmitriy.losev.firebase.data.dto

import com.google.firebase.database.IgnoreExtraProperties
import dmitriy.losev.core.models.types.LessonStatus
import dmitriy.losev.core.models.types.PaidStatus
import java.time.ZoneId

@IgnoreExtraProperties
data class TaskDTO(
    val id: String? = null,
    val lessonId: String? = null,
    val periodId: String? = null,
    val studentOrGroupId: String? = null,
    val subjectId: String? = null,
    val dateTime: Long = 0,
    val zoneId: String = ZoneId.systemDefault().id,
    val status: String = LessonStatus.PLANNED.name,
    val paidStatus: String = PaidStatus.NO_PAID.name
)