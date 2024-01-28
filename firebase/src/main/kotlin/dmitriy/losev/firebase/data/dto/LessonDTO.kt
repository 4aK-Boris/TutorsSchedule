package dmitriy.losev.firebase.data.dto

import com.google.firebase.database.IgnoreExtraProperties
import dmitriy.losev.core.models.types.LessonType

@IgnoreExtraProperties
data class LessonDTO(
    val id: String? = null,
    val type: String = LessonType.STUDENT.name,
    val studentOrGroupId: String? = null,
    val subjectId: String? = null,
    val price: Int = 0,
    val duration: Int = 0
)