package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await



class FirebaseEmailVerificationUseCase(private val firebaseGetUserUseCase: FirebaseGetUserUseCase) : FirebaseBaseUseCase() {

    suspend fun sendVerificationMessage() {
        sendVerificationMessage(user = firebaseGetUserUseCase.getUserWithException())
    }

    suspend fun sendVerificationMessage(user: FirebaseUser) {
        user.sendEmailVerification().await()
    }
}