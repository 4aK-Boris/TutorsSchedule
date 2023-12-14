package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase

class FirebaseGetEmailUseCase(private val firebaseGetUserUseCase: FirebaseGetUserUseCase) : FirebaseBaseUseCase() {

    suspend fun getEmail(): String? {
        return getEmail(user = firebaseGetUserUseCase.getUserWithException())
    }

    fun getEmail(user: FirebaseUser): String? {
        return user.email
    }
}