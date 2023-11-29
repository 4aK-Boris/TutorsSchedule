package dmitriy.losev.profile.domain.usecases

import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.domain.usecases.FirebaseAvatarUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseEmailUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseEmailVerifiedUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseFirstNameUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseLastNameUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseProviderUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.domain.models.UserData

class ProfileUserDataUseCase(
    errorHandler: ErrorHandler,
    private val firebaseAvatarUseCase: FirebaseAvatarUseCase,
    private val firebaseFirstNameUseCase: FirebaseFirstNameUseCase,
    private val firebaseLastNameUseCase: FirebaseLastNameUseCase,
    private val firebaseEmailUseCase: FirebaseEmailUseCase,
    private val firebaseProviderUseCase: FirebaseProviderUseCase,
    private val firebaseEmailVerifiedUseCase: FirebaseEmailVerifiedUseCase
) : ProfileBaseUseCase(errorHandler) {

    suspend fun getUserData(user: FirebaseUser): Result<UserData> {
        return safeReturnCallWithFiveNullable(
            call1 = { firebaseAvatarUseCase.getAvatarUri(user) },
            call2 = { firebaseFirstNameUseCase.getFirstName(user) },
            call3 = { firebaseLastNameUseCase.getLastName(user) },
            call4 = { firebaseEmailUseCase.getEmail(user) },
            call5 = { firebaseProviderUseCase.getProvider(user) },
            call6 = { firebaseEmailVerifiedUseCase.isEmailVerified(user) }
        ).processingResult { (avatarUri, firstName, lastName, email, provider, isEmailVerified) ->
            createUserData(avatarUri, firstName, lastName, email, provider, isEmailVerified)
        }
    }

    private suspend fun createUserData(
        avatarUri: Uri?,
        firstName: String?,
        lastName: String?,
        email: String?,
        provider: String?,
        isEmailVerified: Boolean
    ): Result<UserData> = safeCall {
        UserData(avatarUri, firstName, lastName, email, provider, isEmailVerified)
    }
}