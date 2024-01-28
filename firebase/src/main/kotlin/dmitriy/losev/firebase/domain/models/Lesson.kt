package dmitriy.losev.firebase.domain.models

import dmitriy.losev.core.models.types.LessonType

data class Lesson(val id: String?, val type: LessonType, val studentOrGroupId: String, val subjectId: String, val price: Int, val duration: Int)