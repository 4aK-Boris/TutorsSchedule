package dmitriy.losev.firebase.domain.usecases.auth

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseAuthUseCase(private val firebaseGetUserUseCase: FirebaseGetUserUseCase) : FirebaseBaseUseCase() {

    suspend fun isAuth(): Boolean {
        return firebaseGetUserUseCase.getUserWithoutException() != null
    }
}