package dmitriy.losev.firebase.domain.models

import dmitriy.losev.firebase.domain.models.types.LessonStatus
import dmitriy.losev.firebase.domain.models.types.PaidStatus
import java.time.ZonedDateTime

data class Task(
    val id: String?,
    val lessonId: String,
    val periodId: String,
    val studentOrGroupId: String,
    val subjectId: String,
    val dateTime: ZonedDateTime,
    val status: LessonStatus,
    val paidStatus: PaidStatus
)