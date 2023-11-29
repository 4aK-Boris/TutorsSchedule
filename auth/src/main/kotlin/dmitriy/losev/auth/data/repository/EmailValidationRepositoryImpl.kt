package dmitriy.losev.auth.data.repository

import android.util.Patterns.EMAIL_ADDRESS
import dmitriy.losev.auth.core.exceptions.EmailValidationException
import dmitriy.losev.auth.core.exceptions.EmptyEmailException
import dmitriy.losev.auth.core.exceptions.UserAbsenceException
import dmitriy.losev.auth.core.exceptions.UserAvailableException
import dmitriy.losev.auth.data.network.AuthenticationNetwork
import dmitriy.losev.auth.domain.repository.EmailValidationRepository

class EmailValidationRepositoryImpl(
    private val authenticationNetwork: AuthenticationNetwork
): EmailValidationRepository {

    override suspend fun checkEmailValidationForRegistration(email: String) {
        checkEmailForEmpty(email)
        checkEmailForValidation(email)
        checkUserAbsence(email)
    }

    override suspend fun checkEmailValidationForResetPassword(email: String) {
        checkEmailForEmpty(email)
        checkEmailForValidation(email)
        checkUserAvailable(email)
    }

    private fun checkEmailForEmpty(email: String) {
        if (email.isBlank()) {
            throw EmptyEmailException()
        }
    }

    private fun checkEmailForValidation(email: String) {
        if (!EMAIL_ADDRESS.matcher(email).matches()) {
            throw EmailValidationException()
        }
    }

    private suspend fun checkUserAvailable(email: String) {
        if (!authenticationNetwork.checkUserAvailable(email).isUserAvailable) {
            throw UserAvailableException()
        }
    }

    private suspend fun checkUserAbsence(email: String) {
        if (!authenticationNetwork.checkUserAbsence(email).isUserAbsence) {
            throw UserAbsenceException()
        }
    }
}