package dmitriy.losev.auth.domain.usecases

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.firebase.domain.models.UserDescription
import dmitriy.losev.firebase.domain.usecases.FirebaseEmailVerificationUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUpdateAvatarUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUpdateDisplayNameUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUpdateEmailUseCase

class AuthenticationUpdateInformationUseCase(
    errorHandler: ErrorHandler,
    private val firebaseUpdateAvatarUseCase: FirebaseUpdateAvatarUseCase,
    private val firebaseUpdateDisplayNameUseCase: FirebaseUpdateDisplayNameUseCase,
    private val firebaseUpdateEmailUseCase: FirebaseUpdateEmailUseCase,
    private val firebaseEmailVerificationUseCase: FirebaseEmailVerificationUseCase
) : AuthenticationBaseUseCase(errorHandler) {

    suspend fun updateInformation(user: FirebaseUser, userDescription: UserDescription) = safeReturnCall(
        call1 = { firebaseUpdateAvatarUseCase.updateAvatarWithImageUri(user, userDescription.imageUri) },
        call2 = { firebaseUpdateDisplayNameUseCase.updateDisplayName(user, userDescription.firstName, userDescription.lastName) },
        call3 = { firebaseUpdateEmailUseCase.updateEmail(user, userDescription.email) }
    ).processingResult {
        firebaseEmailVerificationUseCase.sendVerificationMessage(user)
    }
}