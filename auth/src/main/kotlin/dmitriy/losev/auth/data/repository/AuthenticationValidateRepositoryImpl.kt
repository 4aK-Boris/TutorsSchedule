package dmitriy.losev.auth.data.repository

import android.util.Patterns.EMAIL_ADDRESS
import android.util.Patterns.PHONE
import dmitriy.losev.auth.core.MAX_LENGTH_NAME
import dmitriy.losev.auth.core.MAX_LENGTH_PASSWORD
import dmitriy.losev.auth.core.MIN_LENGTH_PASSWORD
import dmitriy.losev.auth.domain.repository.AuthenticationValidateRepository

class AuthenticationValidateRepositoryImpl: AuthenticationValidateRepository {

    override suspend fun checkMaxLengthFirstName(firstName: String): Boolean {
        return firstName.length <= MAX_LENGTH_NAME
    }

    override suspend fun checkMaxLengthLastName(lastName: String): Boolean {
        return lastName.length <= MAX_LENGTH_NAME
    }

    override suspend fun checkEmailForEmptiness(email: String): Boolean {
        return email.isNotBlank()
    }

    override suspend fun checkEmail(email: String): Boolean {
        return EMAIL_ADDRESS.matcher(email).matches()
    }

    override suspend fun checkPasswordForEmptiness(password: String): Boolean {
        return password.isNotBlank()
    }

    override suspend fun checkMinLengthPassword(password: String): Boolean {
        return password.length >= MIN_LENGTH_PASSWORD
    }

    override suspend fun checkMaxLengthPassword(password: String): Boolean {
        return password.length <= MAX_LENGTH_PASSWORD
    }

    override suspend fun checkPasswordForLowerCaseLetter(password: String): Boolean {
        return password.any { char -> char.isLetter() && char.isLowerCase() }
    }

    override suspend fun checkPasswordForUpperCaseLetter(password: String): Boolean {
        return password.any { char -> char.isLetter() && char.isUpperCase() }
    }

    override suspend fun checkPasswordsForSimilarity(
        password1: String,
        password2: String
    ): Boolean {
        return password1.equals(other = password2, ignoreCase = false)
    }

    override suspend fun checkPhoneNumberForEmptiness(phoneNumber: String): Boolean {
        return phoneNumber.isNotBlank()
    }

    override suspend fun checkPhoneNumber(phoneNumber: String): Boolean {
        return PHONE.matcher(phoneNumber).matches()
    }
}