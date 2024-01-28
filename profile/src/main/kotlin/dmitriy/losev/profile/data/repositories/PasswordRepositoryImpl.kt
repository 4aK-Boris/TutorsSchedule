package dmitriy.losev.profile.data.repositories

import dmitriy.losev.profile.core.exception.DifferentPasswordsException
import dmitriy.losev.profile.core.exception.EmptyNewPasswordException
import dmitriy.losev.profile.core.exception.EmptyOldPasswordException
import dmitriy.losev.profile.core.exception.EmptyPasswordException
import dmitriy.losev.profile.core.exception.MaxLengthPasswordException
import dmitriy.losev.profile.core.exception.MinLengthPasswordException
import dmitriy.losev.profile.core.exception.PasswordLowerCaseLetterException
import dmitriy.losev.profile.core.exception.PasswordUpperCaseLetterException
import dmitriy.losev.profile.core.exception.SimilarOldAndNewPasswordException
import dmitriy.losev.profile.domain.repositories.PasswordRepository

class PasswordRepositoryImpl: PasswordRepository {


    override suspend fun checkPassword(oldPassword: String, newPassword1: String, newPassword2: String) {
        checkOldPasswordForEmptiness(oldPassword)
        checkNewPasswordForEmptiness(newPassword1)
        checkPasswordsForSimilarity(newPassword1, newPassword2)
        checkOldAndNewPasswordsForSimilarity(oldPassword, newPassword1)
        checkMinLengthPassword(newPassword1)
        checkMaxLengthPassword(newPassword1)
        checkPasswordForLowerCaseLetter(newPassword1)
        checkPasswordForUpperCaseLetter(newPassword1)
    }

    override suspend fun checkPassword(password: String) {
        checkPasswordForEmptiness(password)
    }

    private fun checkPasswordForEmptiness(password: String) {
        val result = password.isBlank()
        if (result) {
            throw EmptyPasswordException()
        }
    }

    private fun checkOldPasswordForEmptiness(password: String) {
        val result = password.isBlank()
        if (result) {
            throw EmptyOldPasswordException()
        }
    }

    private fun checkNewPasswordForEmptiness(password: String) {
        val result = password.isBlank()
        if (result) {
            throw EmptyNewPasswordException()
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

    private fun checkOldAndNewPasswordsForSimilarity(oldPassword: String, newPassword: String) {
        val result = oldPassword.equals(other = newPassword, ignoreCase = false)
        if (result) {
            throw SimilarOldAndNewPasswordException()
        }
    }

    companion object {
        private const val MIN_LENGTH_PASSWORD = 8
        private const val MAX_LENGTH_PASSWORD = 64
    }
}