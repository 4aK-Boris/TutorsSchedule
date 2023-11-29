package dmitriy.losev.firebase.domain.usecases

import android.content.IntentSender
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.core.FirebaseBaseUseCase

class FirebaseActivityAuthUseCase(errorHandler: ErrorHandler): FirebaseBaseUseCase(errorHandler) {

    suspend fun authWithActivity(
        intentSender: IntentSender,
        launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>
    ): Result<Unit> = safeCall {
        launcher.launch(IntentSenderRequest.Builder(intentSender).build())
    }
}