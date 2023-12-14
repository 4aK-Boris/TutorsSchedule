package dmitriy.losev.firebase.usecases.auth

import android.content.IntentSender
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseActivityAuthUseCase
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseActivityAuthUseCaseTest {

    private val intentSender = mockk<IntentSender>(relaxed = true)
    private val launcher = mockk<ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>>(relaxed = true)

    private val firebaseActivityAuthUseCase = FirebaseActivityAuthUseCase()

    @Test
    fun testAuthWithActivity(): Unit = runBlocking {

        firebaseActivityAuthUseCase.authWithActivity(intentSender, launcher)

        verify { launcher.launch(any()) }
    }
}