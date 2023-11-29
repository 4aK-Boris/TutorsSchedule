package dmitriy.losev.profile.data.repositories

import android.util.Patterns.EMAIL_ADDRESS
import dmitriy.losev.profile.core.exception.EmailValidationException
import dmitriy.losev.profile.core.exception.EmptyEmailException
import dmitriy.losev.profile.core.exception.UserAvailableException
import dmitriy.losev.profile.data.network.ProfileNetwork
import dmitriy.losev.profile.domain.repositories.EmailRepository

class EmailRepositoryImpl(
    private val profileNetwork: ProfileNetwork
): EmailRepository {

    override suspend fun checkEmailValidation(email: String) {
        checkEmailForEmpty(email)
        checkEmailForValidation(email)
        checkUserAvailability(email)
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

    private suspend fun checkUserAvailability(email: String) {
        if (profileNetwork.checkUserAvailability(email).isUserAvailable) {
            throw UserAvailableException()
        }
    }
}