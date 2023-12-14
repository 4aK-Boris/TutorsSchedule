package dmitriy.losev.firebase.usecases.auth

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dmitriy.losev.core.core.ResourcesManager
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseGoogleAuthUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FirebaseGoogleAuthUseCaseTest {

    private val signInClient = mockk<SignInClient>()
    private val result = mockk<BeginSignInResult>()
    private val authResult = mockk<AuthResult>()
    private val signInCredential = mockk<SignInCredential>()
    private val pendingIntent = mockk<PendingIntent>()
    private val intentSender = mockk<IntentSender>()
    private val intent = mockk<Intent>()

    private val auth = mockk<FirebaseAuth>()
    private val resourcesManager = mockk<ResourcesManager>(relaxed = true)

    private val task = FirebaseTask(data = result)
    private val authTask = FirebaseTask(data = authResult)

    private val firebaseGoogleAuthUseCase = FirebaseGoogleAuthUseCase(auth, resourcesManager)

    @AfterEach
    fun setUp() {
        mockkObject(GoogleAuthProvider::class)
    }

    @BeforeEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testAuthWithGoogle(): Unit = runBlocking {

        every { signInClient.beginSignIn(any()) } returns task
        every { result.pendingIntent } returns pendingIntent
        every { pendingIntent.intentSender } returns intentSender

        firebaseGoogleAuthUseCase.authWithGoogle(signInClient)

        verify { signInClient.beginSignIn(any()) }
        verify { result.pendingIntent }
        verify { pendingIntent.intentSender }
    }

    @Test
    fun testAuthWithGoogleNotNullIntent(): Unit = runBlocking {

        every { signInClient.getSignInCredentialFromIntent(intent) } returns signInCredential
        every { signInCredential.googleIdToken } returns TOKEN
        every { auth.signInWithCredential(any()) } returns authTask

        firebaseGoogleAuthUseCase.authWithGoogleIntent(intent, signInClient)

        verify { signInClient.getSignInCredentialFromIntent(intent) }
        verify { signInCredential.googleIdToken }
        verify { auth.signInWithCredential(any()) }
    }

    companion object {
        private const val TOKEN = "token"
    }
}