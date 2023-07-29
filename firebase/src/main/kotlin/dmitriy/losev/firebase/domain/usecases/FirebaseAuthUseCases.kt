package dmitriy.losev.firebase.domain.usecases

import android.app.Application
import android.content.Intent
import android.content.IntentSender
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.userProfileChangeRequest
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.core.exception.ErrorHandler
import dmitriy.losev.firebase.R
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.EmptyUserData
import dmitriy.losev.firebase.core.exception.GoogleAuthIsNotSuccess
import dmitriy.losev.firebase.domain.models.UserData
import dmitriy.losev.firebase.domain.models.UserDescription
import kotlinx.coroutines.tasks.await

class FirebaseAuthUseCases(
    errorHandler: ErrorHandler,
    private val application: Application,
    private val auth: FirebaseAuth,
    private val firebaseStorageUseCases: FirebaseStorageUseCases
) : FirebaseBaseUseCase(errorHandler = errorHandler) {

    val user: FirebaseUser?
        get() = auth.currentUser

    val userData: UserData?
        get() = auth.currentUser?.run {
            UserData(
                userId = uid,
                username = displayName,
                profilePictureUrl = photoUrl,
                email = email,
                isEmailVerified = isEmailVerified
            )
        }

    suspend fun authWithGoogle(client: SignInClient): Result<IntentSender> = safeCall {
        client.beginSignIn(buildSignInRequest()).await().pendingIntent.intentSender
    }

    suspend fun authWithActivity(
        intentSender: IntentSender,
        launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>
    ): Result<Unit> = safeCall {
        launcher.launch(IntentSenderRequest.Builder(intentSender).build())
    }

    suspend fun authWithGoogleIntent(intent: Intent?, client: SignInClient) = safeCall {
        intent?.let {
            val credential = client.getSignInCredentialFromIntent(intent)
            val googleIdToken = credential.googleIdToken
            val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
            auth.signInWithCredential(googleCredentials).await()
        } ?: throw GoogleAuthIsNotSuccess()
    }

    suspend fun authWithEmail(email: String, password: String): Result<Unit> = safeCall {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun registrationWithEmail(email: String, password: String) = safeCall {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun updateInformation(
        user: FirebaseUser,
        userDescription: UserDescription
    ) = safeReturnCallUnit {
        updateInformation(
            user = user,
            firstName = userDescription.firstName,
            lastName = userDescription.lastName,
            imageUri = userDescription.imageUri
        )
    }

    private suspend fun updateInformation(
        user: FirebaseUser,
        firstName: String,
        lastName: String,
        imageUri: Uri?
    ) = safeReturnCall {
        firebaseStorageUseCases.uploadAvatar(imageUri = imageUri)
            .processingResult(onSuccess = { resultUri ->
                uploadInformation(
                    user = user,
                    firstName = firstName,
                    lastName = lastName,
                    resultUri = resultUri
                )
            }, onError = {
                uploadInformation(
                    user = user,
                    firstName = firstName,
                    lastName = lastName,
                    resultUri = null
                )
            })
    }

    private suspend fun uploadInformation(
        user: FirebaseUser,
        firstName: String,
        lastName: String,
        resultUri: Uri?
    ) = safeCall {
        val profileUpdates = userProfileChangeRequest {
            displayName = "$lastName $firstName"
            photoUri = resultUri
        }
        user.updateProfile(profileUpdates).await()
    }


    suspend fun sendEmailVerification(user: FirebaseUser) = safeCallUnit {
        user.sendEmailVerification().await()
    }

    suspend fun sendPasswordResetEmail(email: String): Result<Unit> = safeCall {
        auth.setLanguageCode("ru")
        auth.sendPasswordResetEmail(email).await()
    }

    suspend fun getUserData(): Result<UserData> = safeCall {
        userData ?: throw EmptyUserData()
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.builder().setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder().setSupported(true)
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(application.getString(R.string.web_client_id)).build()
        ).setAutoSelectEnabled(true).build()
    }
}