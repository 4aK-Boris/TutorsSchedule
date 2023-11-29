package dmitriy.losev.vk.domain.usecases

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.firebase.domain.usecases.FirebaseEmailVerificationUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUpdateAvatarUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUpdateDisplayNameUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUpdateEmailUseCase
import dmitriy.losev.vk.core.VkBaseUseCase

class VkUpdateInformationUseCase(
    errorHandler: ErrorHandler,
    private val firebaseUpdateAvatarUseCase: FirebaseUpdateAvatarUseCase,
    private val firebaseUpdateDisplayNameUseCase: FirebaseUpdateDisplayNameUseCase,
    private val firebaseUpdateEmailUseCase: FirebaseUpdateEmailUseCase,
    private val firebaseEmailVerificationUseCase: FirebaseEmailVerificationUseCase
) : VkBaseUseCase(errorHandler) {

    suspend fun updateInformation(user: FirebaseUser, avatarUrl: String?, firstName: String, lastName: String, email: String?) = safeReturnCall(
        call1 = {
            val avatarUri = avatarUrl?.let { Uri.parse(avatarUrl) }
            firebaseUpdateAvatarUseCase.updateAvatarWithImageUrl(user, avatarUri)
        },
        call2 = { firebaseUpdateDisplayNameUseCase.updateDisplayName(user, firstName, lastName) },
        call3 = { firebaseUpdateEmailUseCase.updateEmail(user, email) }
    ).processingResult {
        firebaseEmailVerificationUseCase.sendVerificationMessage(user)
    }
}