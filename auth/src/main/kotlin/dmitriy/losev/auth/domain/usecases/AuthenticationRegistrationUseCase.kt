package dmitriy.losev.auth.domain.usecases

import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.core.EMPTY_STRING
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseEmailRegistrationUseCase

class AuthenticationRegistrationUseCase(
    private val authenticationPasswordUseCase: AuthenticationPasswordUseCase,
    private val authenticationUpdateInformationUseCase: AuthenticationUpdateInformationUseCase,
    private val firebaseEmailRegistrationUseCase: FirebaseEmailRegistrationUseCase
) : AuthenticationBaseUseCase() {

    suspend fun registration(firstName: String, lastName: String, patronymic: String, email: String, password1: String, password2: String) {
        authenticationPasswordUseCase.checkPassword(password1, password2)
        firebaseEmailRegistrationUseCase.registrationWithEmail(email, password1)
        authenticationUpdateInformationUseCase.updateInformation(
            firstName = firstName,
            lastName = lastName,
            patronymic = patronymic,
            phoneNumber = EMPTY_STRING,
            email = email
        )
    }
}