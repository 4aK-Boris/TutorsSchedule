package dmitriy.losev.firebase.data.dto

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class GroupDTO(val id: String? = null, val name: String? = null)