package dmitriy.losev.firebase.domain.usecases.auth

import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dmitriy.losev.core.ResourcesManager
import dmitriy.losev.firebase.R
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.GoogleAuthIsNotSuccessException
import kotlinx.coroutines.tasks.await

class FirebaseGoogleAuthUseCase(
    private val auth: FirebaseAuth,
    private val resourcesManager: ResourcesManager
): FirebaseBaseUseCase() {

    suspend fun authWithGoogle(client: SignInClient): IntentSender = convertException {
        client.beginSignIn(buildSignInRequest()).await().pendingIntent.intentSender
    }

    suspend fun authWithGoogleIntent(intent: Intent?, client: SignInClient): Unit = convertException {
        intent?.let {
            val credential = client.getSignInCredentialFromIntent(intent)
            val googleIdToken = credential.googleIdToken
            val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
            auth.signInWithCredential(googleCredentials).await()
        } ?: throw GoogleAuthIsNotSuccessException()
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.builder().setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder().setSupported(true)
                .setFilterByAuthorizedAccounts(true)
                .setServerClientId(resourcesManager.getString(R.string.web_client_id)).build()
        ).setAutoSelectEnabled(true).build()
    }
}