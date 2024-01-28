package dmitriy.losev.auth.domain.usecases

import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseEmailVerificationUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetFirstNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetLastNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateEmailUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateFirstNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateLastNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdatePatronymicUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdatePhoneNumberUseCase

class AuthenticationUpdateInformationUseCase(
    private val firebaseUpdateFirstNameUseCase: FirebaseUpdateFirstNameUseCase,
    private val firebaseUpdateLastNameUseCase: FirebaseUpdateLastNameUseCase,
    private val firebaseUpdatePatronymicUseCase: FirebaseUpdatePatronymicUseCase,
    private val firebaseUpdatePhoneNumberUseCase: FirebaseUpdatePhoneNumberUseCase,
    private val firebaseUpdateEmailUseCase: FirebaseUpdateEmailUseCase,
    private val firebaseEmailVerificationUseCase: FirebaseEmailVerificationUseCase,
    private val firebaseGetFirstNameUseCase: FirebaseGetFirstNameUseCase,
    private val firebaseGetLastNameUseCase: FirebaseGetLastNameUseCase
) : AuthenticationBaseUseCase() {

    suspend fun updateInformation(firstName: String, lastName: String, patronymic: String, phoneNumber: String, email: String) {
        launchFun(
            f1 = { firebaseUpdateFirstNameUseCase.updateFirstName(firstName) },
            f2 = { firebaseUpdateLastNameUseCase.updateLastName(lastName) },
            f3 = { firebaseUpdatePatronymicUseCase.updatePatronymic(patronymic) },
            f4 = { firebaseUpdatePhoneNumberUseCase.updatePhoneNumber(phoneNumber) },
            f5 = { firebaseUpdateEmailUseCase.updateEmail(email) }
        )
        firebaseEmailVerificationUseCase.sendVerificationMessage()
    }

    suspend fun firstUpdateInformation(firstName: String, lastName: String) {
        launchFun(
            f1 = { firstUpdateFirstName(firstName) },
            f2 = { firstUpdateLastName(lastName) }
        )
    }

    private suspend fun firstUpdateFirstName(firstName: String) {
        firebaseGetFirstNameUseCase.getFirstName().ifBlank {
            firebaseUpdateFirstNameUseCase.updateFirstName(firstName)
        }
    }

    private suspend fun firstUpdateLastName(lastName: String) {
        firebaseGetLastNameUseCase.getLastName().ifBlank {
            firebaseUpdateLastNameUseCase.updateLastName(lastName)
        }
    }
}