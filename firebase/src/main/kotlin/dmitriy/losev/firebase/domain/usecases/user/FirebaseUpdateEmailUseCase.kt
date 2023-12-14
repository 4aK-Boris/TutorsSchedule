package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseUpdateEmailUseCase(private val firebaseGetUserUseCase: FirebaseGetUserUseCase) : FirebaseBaseUseCase() {

    suspend fun updateEmail(email: String) {
        updateEmail(user = firebaseGetUserUseCase.getUserWithException(), email = email)
    }

    suspend fun updateEmail(user: FirebaseUser, email: String) {
        user.verifyBeforeUpdateEmail(email).await()
    }
}