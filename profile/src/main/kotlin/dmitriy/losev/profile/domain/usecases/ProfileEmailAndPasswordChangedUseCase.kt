package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.firebase.domain.usecases.auth.FirebaseProviderUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileEmailAndPasswordChangedUseCase(private val firebaseProviderUseCase: FirebaseProviderUseCase) : ProfileBaseUseCase() {

    suspend fun hasChangedEmailAndPassword(): Boolean {
        return firebaseProviderUseCase.getProvider() == PROVIDER_NAME
    }

    companion object {
        private const val PROVIDER_NAME = "password"
    }
}