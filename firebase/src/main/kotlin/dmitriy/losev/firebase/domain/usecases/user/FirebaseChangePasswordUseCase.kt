package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import kotlinx.coroutines.tasks.await

class FirebaseChangePasswordUseCase(private val firebaseGetUserUseCase: FirebaseGetUserUseCase) : FirebaseBaseUseCase() {

    suspend fun changePassword(password: String) {
        changePassword(user = firebaseGetUserUseCase.getUserWithException(), password = password)
    }

    suspend fun changePassword(user: FirebaseUser, password: String): Unit = convertException {
        user.updatePassword(password).await()
    }
}