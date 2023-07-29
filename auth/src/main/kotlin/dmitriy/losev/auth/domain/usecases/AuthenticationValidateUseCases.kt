package dmitriy.losev.auth.domain.usecases

import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.core.exceptions.DifferentPasswordsException
import dmitriy.losev.auth.core.exceptions.EmptyEmailException
import dmitriy.losev.auth.core.exceptions.EmptyPasswordException
import dmitriy.losev.auth.core.exceptions.InvalidEmailException
import dmitriy.losev.auth.core.exceptions.LowerCaseLetterException
import dmitriy.losev.auth.core.exceptions.MaxLengthFirstNameException
import dmitriy.losev.auth.core.exceptions.MaxLengthLastNameException
import dmitriy.losev.auth.core.exceptions.MaxLengthPasswordException
import dmitriy.losev.auth.core.exceptions.MinLengthPasswordException
import dmitriy.losev.auth.core.exceptions.UpperCaseLetterException
import dmitriy.losev.auth.domain.repository.AuthenticationValidateRepository
import dmitriy.losev.core.exception.ErrorHandler

class AuthenticationValidateUseCases(
    errorHandler: ErrorHandler,
    private val authenticationValidateRepository: AuthenticationValidateRepository
) : AuthenticationBaseUseCase(errorHandler = errorHandler) {

    private suspend fun checkMaxLengthFirstName(firstName: String) = safeCall {
        if (!authenticationValidateRepository.checkMaxLengthFirstName(firstName = firstName)) {
            throw MaxLengthFirstNameException()
        }
    }

    private suspend fun checkMaxLengthLastName(lastName: String) = safeCall {
        if (!authenticationValidateRepository.checkMaxLengthLastName(lastName = lastName)) {
            throw MaxLengthLastNameException()
        }
    }

    private suspend fun checkEmailForEmptiness(email: String) = safeCall {
        if (!authenticationValidateRepository.checkEmailForEmptiness(email = email)) {
            throw EmptyEmailException()
        }
    }

    private suspend fun checkEmail(email: String) = safeCall {
        if (!authenticationValidateRepository.checkEmailForEmptiness(email = email)) {
            throw InvalidEmailException()
        }
    }

    private suspend fun checkPasswordForEmptiness(password: String) = safeCall {
        if (!authenticationValidateRepository.checkPasswordForEmptiness(password = password)) {
            throw EmptyPasswordException()
        }
    }

    private suspend fun checkMinLengthPassword(password: String) = safeCall {
        if (!authenticationValidateRepository.checkMinLengthPassword(password = password)) {
            throw MinLengthPasswordException()
        }
    }

    private suspend fun checkMaxLengthPassword(password: String) = safeCall {
        if (!authenticationValidateRepository.checkMaxLengthPassword(password = password)) {
            throw MaxLengthPasswordException()
        }
    }

    private suspend fun checkPasswordForLowerCaseLetter(password: String) = safeCall {
        if (!authenticationValidateRepository.checkPasswordForLowerCaseLetter(password = password)) {
            throw LowerCaseLetterException()
        }
    }

    private suspend fun checkPasswordForUpperCaseLetter(password: String) = safeCall {
        if (!authenticationValidateRepository.checkPasswordForUpperCaseLetter(password = password)) {
            throw UpperCaseLetterException()
        }
    }

    private suspend fun checkPasswordsForSimilarity(password1: String, password2: String) =
        safeCall {
            if (!authenticationValidateRepository.checkPasswordsForSimilarity(
                    password1 = password1,
                    password2 = password2
                )
            ) {
                throw DifferentPasswordsException()
            }
        }

    suspend fun isValidateFirstName(firstName: String) = safeReturnCall {
        checkMaxLengthFirstName(firstName = firstName)
    }

    suspend fun isValidateLastName(lastName: String) = safeReturnCall {
        checkMaxLengthLastName(lastName = lastName)
    }

    suspend fun isValidateEmail(email: String) =
        safeReturnCall(call1 = { checkEmail(email = email) },
            call2 = { checkEmailForEmptiness(email = email) })

    suspend fun isValidatePassword(password1: String, password2: String) = safeReturnCall(
        call1 = { checkPasswordForEmptiness(password = password1) },
        call2 = { checkMinLengthPassword(password = password1) },
        call3 = { checkMaxLengthPassword(password = password1) },
        call4 = { checkPasswordForLowerCaseLetter(password = password1) },
        call5 = { checkPasswordForUpperCaseLetter(password = password1) },
        call6 = { checkPasswordsForSimilarity(password1 = password1, password2 = password2) },
    )
}