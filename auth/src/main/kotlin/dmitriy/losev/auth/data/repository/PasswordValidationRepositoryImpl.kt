package dmitriy.losev.auth.data.repository

import dmitriy.losev.auth.core.exceptions.DifferentPasswordsException
import dmitriy.losev.auth.core.exceptions.EmptyPasswordException
import dmitriy.losev.auth.core.exceptions.MaxLengthPasswordException
import dmitriy.losev.auth.core.exceptions.MinLengthPasswordException
import dmitriy.losev.auth.core.exceptions.PasswordLowerCaseLetterException
import dmitriy.losev.auth.core.exceptions.PasswordUpperCaseLetterException
import dmitriy.losev.auth.domain.repository.PasswordValidationRepository

class PasswordValidationRepositoryImpl: PasswordValidationRepository {

    override suspend fun checkPassword(password1: String, password2: String) {
        checkPasswordForEmptiness(password1)
        checkMinLengthPassword(password1)
        checkMaxLengthPassword(password1)
        checkPasswordForLowerCaseLetter(password1)
        checkPasswordForUpperCaseLetter(password1)
        checkPasswordsForSimilarity(password1, password2)
    }

    private fun checkPasswordForEmptiness(password: String) {
        val result = password.isBlank()
        if (result) {
            throw EmptyPasswordException()
        }
    }

    private fun checkMinLengthPassword(password: String) {
        val result = password.length < MIN_LENGTH_PASSWORD
        if (result) {
            throw MinLengthPasswordException()
        }
    }

    private fun checkMaxLengthPassword(password: String) {
        val result =  password.length > MAX_LENGTH_PASSWORD
        if (result) {
            throw MaxLengthPasswordException()
        }
    }

    private fun checkPasswordForLowerCaseLetter(password: String) {
        val result = password.none { char -> char.isLetter() && char.isLowerCase() }
        if (result) {
            throw PasswordLowerCaseLetterException()
        }
    }

    private fun checkPasswordForUpperCaseLetter(password: String) {
        val result = password.none { char -> char.isLetter() && char.isUpperCase() }
        if (result) {
            throw PasswordUpperCaseLetterException()
        }
    }

    private fun checkPasswordsForSimilarity(password1: String, password2: String) {
        val result = !password1.equals(other = password2, ignoreCase = false)
        if (result) {
            throw DifferentPasswordsException()
        }
    }

    companion object {
        private const val MIN_LENGTH_PASSWORD = 8
        private const val MAX_LENGTH_PASSWORD = 64
    }
}