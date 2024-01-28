package dmitriy.losev.auth.domain.usecases

import dmitriy.losev.firebase.domain.usecases.auth.FirebaseAuthUseCase

class AuthenticationCheckAuthUseCase(private val firebaseAuthUseCase: FirebaseAuthUseCase) {

    suspend fun checkAuth(): Boolean {
        return firebaseAuthUseCase.isAuth()
    }
}