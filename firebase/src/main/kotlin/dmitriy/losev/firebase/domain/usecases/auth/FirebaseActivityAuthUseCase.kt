package dmitriy.losev.firebase.domain.usecases.auth

import android.content.IntentSender
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import dmitriy.losev.firebase.core.FirebaseBaseUseCase

class FirebaseActivityAuthUseCase : FirebaseBaseUseCase() {

    fun authWithActivity(
        intentSender: IntentSender,
        launcher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>
    ) {
        launcher.launch(IntentSenderRequest.Builder(intentSender).build())
    }
}