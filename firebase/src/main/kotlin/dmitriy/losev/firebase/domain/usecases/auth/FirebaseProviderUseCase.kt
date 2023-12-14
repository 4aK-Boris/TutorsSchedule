package dmitriy.losev.firebase.domain.usecases.auth

import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseProviderUseCase(private val firebaseGetUserUseCase: FirebaseGetUserUseCase) : FirebaseBaseUseCase() {

    suspend fun getProvider(): String? {
        return getProvider(user = firebaseGetUserUseCase.getUserWithException())
    }

    fun getProvider(user: FirebaseUser): String? {
        return user.providerData.lastOrNull()?.providerId
    }
}