package dmitriy.losev.auth.presentation.viewmodels

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.navigation.NavController
import com.google.android.gms.auth.api.identity.SignInClient
import dmitriy.losev.auth.core.exceptions.UNSUPPORTED_API_CALL_EXCEPTION
import dmitriy.losev.auth.domain.usecases.screens.StartScreenUseCases
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.core.runOnBackground
import dmitriy.losev.exception.ErrorHandler

class StartScreenViewModel(
    errorHandler: ErrorHandler,
    private val startScreenUseCases: StartScreenUseCases
) : BaseViewModel(errorHandler = errorHandler) {

    fun authWithGoogle(
        client: SignInClient,
        launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>
    ) = processing {
        startScreenUseCases.authWithGoogle(client = client, launcher = launcher)
    }

    fun authWithGoogleIntent(result: ActivityResult, client: SignInClient, navController: NavController) = runOnBackground {
            if (result.resultCode == Activity.RESULT_OK) {
                startScreenUseCases.authWithGoogleIntent(
                    intent = result.data,
                    client = client,
                    navController = navController
                ).processing()
            }
        }

    fun authWithYandex(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) = processing {
        startScreenUseCases.authWithYandex(launcher = launcher)
    }

    fun authWithYandexIntent(result: ActivityResult, navController: NavController) = runOnBackground {
        if (result.resultCode == Activity.RESULT_OK) {
            startScreenUseCases.authWithYandexIntent(resultCode = result.resultCode, intent = result.data).processing {
                println(it)
            }
        }
    }

    fun navigateToLoginScreen(navController: NavController) = processing {
        startScreenUseCases.navigateToLoginScreen(navController = navController)
    }

    fun navigateToDataScreen(navController: NavController) = processing {
        startScreenUseCases.navigateToDataScreen(navController = navController)
    }

    override val errorMap: Map<Int, String>
        get() = mapOf(UNSUPPORTED_API_CALL_EXCEPTION to "Войдите хотя бы в один аккаунт Google на устройстве!")
}
