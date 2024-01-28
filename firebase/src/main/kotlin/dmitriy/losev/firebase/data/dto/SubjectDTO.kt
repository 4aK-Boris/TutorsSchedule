package dmitriy.losev.firebase.data.dto

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class SubjectDTO(val id: String? = null, val name: String? = null, val price: Int? = null)