package dmitriy.losev.firebase.core.usecases

import android.net.Uri
import com.google.firebase.auth.userProfileChangeRequest
import dmitriy.losev.firebase.core.AVATAR_URI
import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.DISPLAY_NAME
import dmitriy.losev.firebase.core.EMAIl
import dmitriy.losev.firebase.core.PASSWORD
import dmitriy.losev.firebase.core.exception.UserNotAuthorizationException
import kotlinx.coroutines.tasks.await

abstract class BaseUserUseCaseTest: BaseUseCaseTest() {

    val user by lazy { auth.currentUser ?: throw UserNotAuthorizationException() }

    val isAuth: Boolean
        get() = auth.currentUser != null

    val isEmailVerified: Boolean
        get() = user.isEmailVerified

    val avatarUri: Uri?
        get() = user.photoUrl

    val displayName: String?
        get() = user.displayName

    val email: String?
        get() = user.email

    suspend fun logIn(password: String) {
        auth.signInWithEmailAndPassword(EMAIl, password).await()
    }

    suspend fun logIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun returnPassword() {
        user.updatePassword(PASSWORD).await()
    }

    suspend fun returnEmail() {
        user.updateEmail(EMAIl).await()
    }

    suspend fun returnAvatar() {
        val profileUpdates = userProfileChangeRequest {
            photoUri = AVATAR_URI
        }
        user.updateProfile(profileUpdates).await()
    }

    suspend fun returnDisplayName() {
        val profileUpdates = userProfileChangeRequest {
            displayName = DISPLAY_NAME
        }
        user.updateProfile(profileUpdates).await()
    }

    suspend fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
    }
}