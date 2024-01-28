package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.firebase.domain.usecases.auth.FirebaseAuthUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileAuthUseCase(private val firebaseAuthUseCase: FirebaseAuthUseCase): ProfileBaseUseCase() {

    suspend fun isAuth(): Boolean {
        return firebaseAuthUseCase.isAuth()
    }
}