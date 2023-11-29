package dmitriy.losev.auth.domain.usecases

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.firebase.domain.usecases.FirebaseActivityAuthUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseGoogleAuthUseCase
import dmitriy.losev.firebase.domain.usecases.FirebaseUserUseCase

class AuthenticationGoogleUseCase(
    errorHandler: ErrorHandler,
    private val firebaseGoogleAuthUseCase: FirebaseGoogleAuthUseCase,
    private val firebaseActivityAuthUseCase: FirebaseActivityAuthUseCase,
    private val firebaseUserUseCase: FirebaseUserUseCase,
    private val authenticationNavigationUseCases: AuthenticationNavigationUseCases
) : AuthenticationBaseUseCase(errorHandler) {

    suspend fun authWithGoogle(client: SignInClient, launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) = safeReturnCall {
        firebaseGoogleAuthUseCase.authWithGoogle(client).processingResult { intentSender ->
            firebaseActivityAuthUseCase.authWithActivity(intentSender, launcher)
        }
    }

    suspend fun authWithGoogleIntent(
        intent: Intent?,
        client: SignInClient,
        authenticationNavigationListener: AuthenticationNavigationListener
    ) = safeReturnCall {
        firebaseGoogleAuthUseCase.authWithGoogleIntent(intent, client).processingResult {
            firebaseUserUseCase.getUserWithException().processingResult {
                authenticationNavigationUseCases.navigateToProfileScreen(authenticationNavigationListener)
            }
        }
    }
}