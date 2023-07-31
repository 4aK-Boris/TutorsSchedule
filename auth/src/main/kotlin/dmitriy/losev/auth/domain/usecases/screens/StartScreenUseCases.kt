package dmitriy.losev.auth.domain.usecases.screens

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.navigation.NavController
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.exception.ErrorHandler
import dmitriy.losev.firebase.domain.usecases.FirebaseAuthUseCases
import okio.internal.commonAsUtf8ToByteArray
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

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

    suspend fun authWithYandex(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) = safeReturnCall {
       firebaseAuthUseCases.authWithYandex(launcher = launcher)
    }

    suspend fun authWithYandexIntent(resultCode: Int, intent: Intent?, navController: NavController) = safeReturnCall {
        firebaseAuthUseCases.authWithYandexIntent(resultCode = resultCode, intent = intent).processingResult { token ->
            firebaseAuthUseCases.authWithToken(token = token).processingResult {
                println(it.user)
                Result.Success(data = "dwadwa")
            }
        }
    }
}