package dmitriy.losev.core.models

import dmitriy.losev.core.models.types.GroupType

data class Group(override var id: String, val name: String, val groupType: GroupType): BaseModel