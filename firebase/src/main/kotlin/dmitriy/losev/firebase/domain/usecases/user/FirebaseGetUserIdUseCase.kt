package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase

class FirebaseGetUserIdUseCase(private val firebaseGetUserUseCase: FirebaseGetUserUseCase) : FirebaseBaseUseCase() {

    suspend fun getUserIdWithoutException(): String? {
        val user = firebaseGetUserUseCase.getUserWithoutException()
        return getUserIdWithoutException(user)
    }

    suspend fun getUserIdWithException(): String {
        val user = firebaseGetUserUseCase.getUserWithException()
        return getUserIdWithException(user)
    }

    fun getUserIdWithoutException(user: FirebaseUser?): String? {
        return user?.uid
    }

    fun getUserIdWithException(user: FirebaseUser): String {
        return user.uid
    }
}