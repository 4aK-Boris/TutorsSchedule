package dmitriy.losev.firebase.data.dto

import com.google.firebase.database.IgnoreExtraProperties
import dmitriy.losev.core.models.types.GroupType

@IgnoreExtraProperties
data class GroupDTO(val id: String? = null, val name: String? = null, val groupType: String = GroupType.NEW.name)