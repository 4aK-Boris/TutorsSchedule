package dmitriy.losev.tutorsschedule.domain.usecases

import android.app.Application
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dmitriy.losev.auth.core.exceptions.AnonymousAuthIsNotSuccess
import dmitriy.losev.auth.core.exceptions.EmailAuthIsNotSuccess
import dmitriy.losev.auth.core.exceptions.GoogleAuthIsNotSuccess
import dmitriy.losev.core.exception.ErrorHandler
import dmitriy.losev.firebase.domain.models.UserData
import dmitriy.losev.tutorsschedule.R
import dmitriy.losev.tutorsschedule.core.AppBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseUseCases(
    errorHandler: ErrorHandler,
    private val auth: FirebaseAuth,
    private val application: Application
): AppBaseUseCase(errorHandler = errorHandler) {

    suspend fun signOut(client: SignInClient) {
        client.signOut().await()
        auth.signOut()
    }

    suspend fun authWithGoogle(client: SignInClient): IntentSender {
        return client.beginSignIn(buildSignInRequest()).await().pendingIntent.intentSender
    }


    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.builder().setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder().setSupported(true)
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(application.getString(R.string.default_web_client_id)).build()
        ).setAutoSelectEnabled(true).build()
    }

    fun authWithGoogleIntent(intent: Intent, client: SignInClient) {
        val credential = client.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        auth.signInWithCredential(googleCredentials).addOnCompleteListener { result ->
            if (!result.isSuccessful) {
                throw GoogleAuthIsNotSuccess()
            }
        }
    }

    fun authWithAnonymous(onSuccess: () -> Unit) {
        auth.signInAnonymously()
            .addOnCompleteListener { result ->
                if (!result.isSuccessful) {
                    throw AnonymousAuthIsNotSuccess()
                }
            }
    }

    fun authWithEmail(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { result ->
            if (!result.isSuccessful) {
                throw EmailAuthIsNotSuccess()
            }
        }
    }

    fun getUserData(): UserData? {
        return auth.currentUser?.run {
            println(photoUrl)
            println(isEmailVerified)
            UserData(
                userId = uid,
                username = displayName,
                profilePictureUrl = photoUrl,
                email = email,
                isEmailVerified = isEmailVerified
            )
        }
    }
}