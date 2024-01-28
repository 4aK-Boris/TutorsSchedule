package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.core.firstChar
import dmitriy.losev.core.toNotNull

class NameMapper {

    fun getName(firstName: String?, lastName: String?, patronymic: String?): String {
        val firstNameNotNull = firstName.toNotNull()
        val lastNameNotNull = lastName.toNotNull()
        val patronymicNotNull = patronymic.toNotNull()
        return "$lastNameNotNull $firstNameNotNull $patronymicNotNull".trim()
    }

    fun getMiddleName(firstName: String?, lastName: String?, patronymic: String?): String {
        val firstNameNotNull = firstName.toNotNull()
        val lastNameNotNull = lastName.toNotNull()
        val patronymicNotNull = patronymic.toNotNull()
        return "$lastNameNotNull $firstNameNotNull ${patronymicNotNull.firstChar()}".trim()
    }

    fun getShortName(firstName: String?, lastName: String?, patronymic: String?): String {
        val firstNameNotNull = firstName.toNotNull()
        val lastNameNotNull = lastName.toNotNull()
        val patronymicNotNull = patronymic.toNotNull()
        return if (firstNameNotNull.isBlank()) {
            "$lastNameNotNull ${patronymicNotNull.firstChar()}".trim()
        } else {
            "$firstNameNotNull ${lastNameNotNull.firstChar()}".trim()
        }
    }
}