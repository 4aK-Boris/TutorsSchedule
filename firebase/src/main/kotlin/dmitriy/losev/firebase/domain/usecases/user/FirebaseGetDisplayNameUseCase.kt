package dmitriy.losev.firebase.domain.usecases.user

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase

class FirebaseGetDisplayNameUseCase(private val firebaseGetUserUseCase: FirebaseGetUserUseCase) : FirebaseBaseUseCase() {

    suspend fun getDisplayName(): String? {
        return firebaseGetUserUseCase.getUserWithException().displayName
    }

    fun getDisplayName(user: FirebaseUser): String? {
        return user.displayName
    }
}