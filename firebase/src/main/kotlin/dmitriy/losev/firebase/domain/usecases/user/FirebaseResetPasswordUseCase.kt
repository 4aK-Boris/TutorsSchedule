package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseAuth
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseResetPasswordUseCase(private val auth: FirebaseAuth): FirebaseBaseUseCase() {

    suspend fun sendPasswordResetEmail(email: String) {
        auth.setLanguageCode(LANGUAGE_CODE)
        auth.sendPasswordResetEmail(email).await()
    }

    companion object {
        private const val LANGUAGE_CODE = "ru"
    }
}