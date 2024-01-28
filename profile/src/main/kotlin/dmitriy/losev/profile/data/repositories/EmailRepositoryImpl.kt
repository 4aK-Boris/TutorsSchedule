package dmitriy.losev.profile.data.repositories

import android.util.Patterns.EMAIL_ADDRESS
import dmitriy.losev.profile.core.exception.EmailValidationException
import dmitriy.losev.profile.core.exception.EmptyEmailException
import dmitriy.losev.profile.core.exception.EmptyNewEmailException
import dmitriy.losev.profile.core.exception.EmptyOldEmailException
import dmitriy.losev.profile.core.exception.NewEmailValidationException
import dmitriy.losev.profile.core.exception.OldEmailValidationException
import dmitriy.losev.profile.core.exception.UserAbsenceException
import dmitriy.losev.profile.core.exception.UserAvailableException
import dmitriy.losev.profile.data.network.ProfileNetwork
import dmitriy.losev.profile.domain.repositories.EmailRepository

class EmailRepositoryImpl(
    private val profileNetwork: ProfileNetwork
): EmailRepository {

    override suspend fun checkEmail(email: String) {
        checkEmailForEmpty(email)
        checkEmailForValidation(email)
    }

    override suspend fun checkOldEmail(email: String) {
        checkOldEmailForEmpty(email)
        checkOldEmailForValidation(email)
    }

    override suspend fun checkNewEmail(email: String) {
        checkNewEmailForEmpty(email)
        checkNewEmailForValidation(email)
    }

    override suspend fun checkOldEmailForChange(email: String) {
        checkOldEmail(email)
        checkUserAvailable(email)
    }

    override suspend fun checkNewEmailForChange(email: String) {
        checkNewEmail(email)
        checkUserAbsence(email)
    }

    private fun checkEmailForEmpty(email: String) {
        if (email.isBlank()) {
            throw EmptyEmailException()
        }
    }

    private fun checkOldEmailForEmpty(email: String) {
        if (email.isBlank()) {
            throw EmptyOldEmailException()
        }
    }

    private fun checkNewEmailForEmpty(email: String) {
        if (email.isBlank()) {
            throw EmptyNewEmailException()
        }
    }

    private fun checkEmailForValidation(email: String) {
        if (!EMAIL_ADDRESS.matcher(email).matches()) {
            throw EmailValidationException()
        }
    }

    private fun checkOldEmailForValidation(email: String) {
        if (!EMAIL_ADDRESS.matcher(email).matches()) {
            throw OldEmailValidationException()
        }
    }

    private fun checkNewEmailForValidation(email: String) {
        if (!EMAIL_ADDRESS.matcher(email).matches()) {
            throw NewEmailValidationException()
        }
    }

    private suspend fun checkUserAvailable(email: String) {
        if (profileNetwork.checkUserAvailable(email).isUserAvailable) {
            throw UserAvailableException()
        }
    }

    private suspend fun checkUserAbsence(email: String) {
        if (profileNetwork.checkUserAbsence(email).isUserAbsence) {
            throw UserAbsenceException()
        }
    }
}