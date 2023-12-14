package dmitriy.losev.firebase.data.dto

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ContactDTO(
    val id: String? = null,
    val name: String? = null,
    val phoneNumber: String? = null
)
