package dmitriy.losev.students.data.repository

import android.util.Patterns
import dmitriy.losev.students.core.exception.EmailValidationException
import dmitriy.losev.students.core.exception.EmptyEmailException
import dmitriy.losev.students.domain.repository.EmailRepository

class EmailRepositoryImpl: EmailRepository {

    override suspend fun checkEmail(email: String) {
        if (email.isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            throw EmailValidationException()
        }
    }

    override suspend fun checkEmailForEmpty(email: String) {
        if (email.isBlank()) {
            throw EmptyEmailException()
        }
    }
}