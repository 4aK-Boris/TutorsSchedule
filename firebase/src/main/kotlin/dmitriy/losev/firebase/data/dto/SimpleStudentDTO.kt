package dmitriy.losev.firebase.data.dto

import com.google.firebase.database.IgnoreExtraProperties
import dmitriy.losev.firebase.domain.models.types.StudentType

@IgnoreExtraProperties
data class SimpleStudentDTO(
    val id: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val nickName: String? = null,
    val studentType: String = StudentType.NEW.name
)
