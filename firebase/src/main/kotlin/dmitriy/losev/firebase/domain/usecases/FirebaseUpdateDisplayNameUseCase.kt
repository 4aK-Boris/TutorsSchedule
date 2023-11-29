package dmitriy.losev.firebase.domain.usecases

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseUpdateDisplayNameUseCase(errorHandler: ErrorHandler) : FirebaseBaseUseCase(errorHandler) {

    suspend fun updateDisplayName(
        user: FirebaseUser,
        firstName: String,
        lastName: String
    ): Result<Unit> = safeCall {
        val profileUpdates = userProfileChangeRequest {
            displayName = "$firstName $lastName"
        }
        user.updateProfile(profileUpdates).await()
    }
}