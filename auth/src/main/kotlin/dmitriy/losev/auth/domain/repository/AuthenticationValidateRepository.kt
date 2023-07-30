package dmitriy.losev.auth.domain.repository

interface AuthenticationValidateRepository {

    suspend fun checkMaxLengthFirstName(firstName: String): Boolean

    suspend fun checkMaxLengthLastName(lastName: String): Boolean

    suspend fun checkEmailForEmptiness(email: String): Boolean

    suspend fun checkEmail(email: String): Boolean

    suspend fun checkPasswordForEmptiness(password: String): Boolean

    suspend fun checkMinLengthPassword(password: String): Boolean

    suspend fun checkMaxLengthPassword(password: String): Boolean

    suspend fun checkPasswordForLowerCaseLetter(password: String): Boolean

    suspend fun checkPasswordForUpperCaseLetter(password: String): Boolean

    suspend fun checkPasswordsForSimilarity(password1: String, password2: String): Boolean

    suspend fun checkPhoneNumberForEmptiness(phoneNumber: String): Boolean

    suspend fun checkPhoneNumber(phoneNumber: String): Boolean
}