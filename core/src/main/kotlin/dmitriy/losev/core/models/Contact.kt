package dmitriy.losev.core.models

data class Contact(
    override var id: String,
    val name: String,
    val shortName: String,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val phoneNumber: String
): BaseModel
