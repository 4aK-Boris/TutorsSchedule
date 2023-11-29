package dmitriy.losev.yandex.domain.usecases

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.firebase.domain.usecases.FirebaseEmailVerificationUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUpdateAvatarUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUpdateDisplayNameUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUpdateEmailUseCase
import dmitriy.losev.yandex.core.YandexBaseUseCase

class YandexUpdateInformationUseCase(
    errorHandler: ErrorHandler,
    private val firebaseUpdateAvatarUseCase: FirebaseUpdateAvatarUseCase,
    private val firebaseUpdateDisplayNameUseCase: FirebaseUpdateDisplayNameUseCase,
    private val firebaseUpdateEmailUseCase: FirebaseUpdateEmailUseCase,
    private val firebaseEmailVerificationUseCase: FirebaseEmailVerificationUseCase
) : YandexBaseUseCase(errorHandler) {

    suspend fun updateInformation(user: FirebaseUser, avatarUrl: String, firstName: String, lastName: String, email: String) = safeReturnCall(
        call1 = { firebaseUpdateAvatarUseCase.updateAvatarWithImageUrl(user, Uri.parse(avatarUrl)) },
        call2 = { firebaseUpdateDisplayNameUseCase.updateDisplayName(user, firstName, lastName) },
        call3 = { firebaseUpdateEmailUseCase.updateEmail(user, email) }
    ).processingResult {
        firebaseEmailVerificationUseCase.sendVerificationMessage(user)
    }
}