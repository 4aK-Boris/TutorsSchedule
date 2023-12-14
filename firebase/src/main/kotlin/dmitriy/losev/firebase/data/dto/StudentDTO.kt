package dmitriy.losev.firebase.data.dto

import com.google.firebase.database.IgnoreExtraProperties
import dmitriy.losev.firebase.domain.models.types.StudentType

@IgnoreExtraProperties
data class StudentDTO(
    val id: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val nickName: String? = null,
    val phoneNumber: String? = null,
    val email: String? = null,
    val skype: String? = null,
    val discord: String? = null,
    val address: String? = null,
    val comment: String? = null,
    val studentType: String = StudentType.NEW.name
)
