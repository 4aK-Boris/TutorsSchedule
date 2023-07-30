package dmitriy.losev.auth.domain.usecases.screens

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.navigation.NavController
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.exception.ErrorHandler
import dmitriy.losev.firebase.domain.usecases.FirebaseAuthUseCases

class StartScreenUseCases(
    errorHandler: ErrorHandler,
    private val firebaseAuthUseCases: FirebaseAuthUseCases,
    private val authenticationNavigationUseCases: AuthenticationNavigationUseCases
) : AuthenticationBaseUseCase(errorHandler = errorHandler) {

    suspend fun navigateToLoginScreen(navController: NavController) = safeReturnCall {
        authenticationNavigationUseCases.navigateToLoginScreen(navController = navController)
    }

    suspend fun navigateToDataScreen(navController: NavController) = safeReturnCall {
        authenticationNavigationUseCases.navigateToDataScreen(navController = navController)
    }

    suspend fun authWithGoogle(
        client: SignInClient,
        launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>
    ) = safeReturnCall {
        firebaseAuthUseCases.authWithGoogle(client)
            .processingResult { intentSender ->
                firebaseAuthUseCases.authWithActivity(
                    intentSender = intentSender,
                    launcher = launcher
                )
            }
    }

    suspend fun authWithGoogleIntent(
        intent: Intent?,
        client: SignInClient,
        navController: NavController
    ) = safeReturnCall {
        firebaseAuthUseCases.authWithGoogleIntent(intent = intent, client = client)
            .processingResult {
                firebaseAuthUseCases.getUserData().processingResult {
                    authenticationNavigationUseCases.navigateToEmptyScreen(navController = navController)
                }
            }
    }
}