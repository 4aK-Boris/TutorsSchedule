package dmitriy.losev.subjects.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SubjectDTO(val id: String, val name: String, val price: Int?)