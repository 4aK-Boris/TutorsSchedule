package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseUpdateDisplayNameUseCase(private val firebaseGetUserUseCase: FirebaseGetUserUseCase) : FirebaseBaseUseCase() {

    suspend fun updateDisplayName(
        firstName: String,
        lastName: String
    ): Unit = convertException {
        updateDisplayName(user = firebaseGetUserUseCase.getUserWithException(), firstName = firstName, lastName = lastName)
    }

    suspend fun updateDisplayName(
        user: FirebaseUser,
        firstName: String,
        lastName: String
    ): Unit = convertException {
        val profileUpdates = userProfileChangeRequest {
            displayName = "$firstName $lastName"
        }
        user.updateProfile(profileUpdates).await()
    }
}