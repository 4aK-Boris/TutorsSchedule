package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase

class FirebaseEmailVerifiedUseCase(private val firebaseGetUserUseCase: FirebaseGetUserUseCase) : FirebaseBaseUseCase() {


    suspend fun isEmailVerified(): Boolean {
        return isEmailVerified(user = firebaseGetUserUseCase.getUserWithException())
    }

    fun isEmailVerified(user: FirebaseUser): Boolean {
        return user.isEmailVerified
    }
}