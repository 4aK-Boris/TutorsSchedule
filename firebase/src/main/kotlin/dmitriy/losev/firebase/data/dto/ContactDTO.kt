package dmitriy.losev.firebase.data.dto

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ContactDTO(
    val id: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val patronymic: String? = null,
    val phoneNumber: String? = null
)
