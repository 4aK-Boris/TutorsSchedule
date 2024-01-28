package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.firebase.domain.usecases.user.FirebaseEmailVerificationUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileEmailVerificationUseCase(private val firebaseEmailVerificationUseCase: FirebaseEmailVerificationUseCase) : ProfileBaseUseCase() {

    suspend fun sendEmailVerification() {
        firebaseEmailVerificationUseCase.sendVerificationMessage()
    }
}