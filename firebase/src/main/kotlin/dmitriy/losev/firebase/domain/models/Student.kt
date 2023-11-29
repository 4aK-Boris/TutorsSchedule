package dmitriy.losev.firebase.domain.models

data class Student(
    val id: String?,
    val firstName: String,
    val lastName: String,
    val nickName: String,
    val name: String,
    val phoneNumber: String,
    val email: String,
    val skype: String,
    val address: String,
    val comment: String,
    val studentType: StudentType
)
