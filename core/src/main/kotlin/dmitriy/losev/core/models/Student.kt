package dmitriy.losev.core.models

import dmitriy.losev.core.models.types.StudentType

data class Student(
    override var id: String,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val name: String,
    val shortName: String,
    val phoneNumber: String,
    val email: String,
    val skype: String,
    val discord: String,
    val comment: String,
    val studentType: StudentType
): BaseModel
