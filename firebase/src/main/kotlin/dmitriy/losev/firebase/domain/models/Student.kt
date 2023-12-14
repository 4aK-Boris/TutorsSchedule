package dmitriy.losev.firebase.domain.models

import dmitriy.losev.firebase.domain.models.types.StudentType

data class Student(
    val id: String?,
    val firstName: String,
    val lastName: String,
    val nickName: String,
    val name: String,
    val phoneNumber: String,
    val email: String,
    val skype: String,
    val discord: String,
    val address: String,
    val comment: String,
    val studentType: StudentType
)
