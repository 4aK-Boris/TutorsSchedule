package dmitriy.losev.firebase.data.dto

import com.google.firebase.database.IgnoreExtraProperties
import dmitriy.losev.core.models.types.StudentType

@IgnoreExtraProperties
data class StudentDTO(
    val id: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val patronymic: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val skype: String? = null,
    val discord: String? = null,
    val comment: String? = null,
    val studentType: String = StudentType.NEW.name
)
