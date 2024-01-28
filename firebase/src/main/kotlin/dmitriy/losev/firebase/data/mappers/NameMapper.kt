package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.firebase.core.toNotNull

class NameMapper {

    fun map(firstName: String?, lastName: String?, nickName: String?): String {
        val firstNameNotNull = firstName.toNotNull()
        val lastNameNotNull = lastName.toNotNull()
        val nickNameNotNull = nickName.toNotNull()
        val name = "$firstNameNotNull $lastNameNotNull".trim()
        return if (nickNameNotNull.isNotBlank()) {
            "$name ($nickNameNotNull)".trim()
        } else {
            name
        }
    }
}