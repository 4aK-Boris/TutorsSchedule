package dmitriy.losev.auth.domain.usecases

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.core.EMPTY_STRING
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseActivityAuthUseCase
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseGoogleAuthUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetDisplayNameUseCase

class AuthenticationGoogleUseCase(
    private val firebaseGoogleAuthUseCase: FirebaseGoogleAuthUseCase,
    private val firebaseActivityAuthUseCase: FirebaseActivityAuthUseCase,
    private val firebaseGetDisplayNameUseCase: FirebaseGetDisplayNameUseCase,
    private val authenticationUpdateInformationUseCase: AuthenticationUpdateInformationUseCase
) : AuthenticationBaseUseCase() {

    suspend fun authWithGoogle(client: SignInClient, launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) {
        val intentSender = firebaseGoogleAuthUseCase.authWithGoogle(client)
        firebaseActivityAuthUseCase.authWithActivity(intentSender, launcher)
    }

    suspend fun authWithGoogleIntent(intent: Intent?, client: SignInClient) {
        firebaseGoogleAuthUseCase.authWithGoogleIntent(intent, client)
        val displayName = firebaseGetDisplayNameUseCase.getDisplayName()?.split(' ')
        val firstName = displayName?.firstOrNull() ?: EMPTY_STRING
        val lastName = displayName?.lastOrNull() ?: EMPTY_STRING
        authenticationUpdateInformationUseCase.firstUpdateInformation(firstName, lastName)
    }
}