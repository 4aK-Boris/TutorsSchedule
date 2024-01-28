package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.firebase.domain.usecases.user.FirebaseDeleteUserUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileDeleteAccountUseCase(private val firebaseDeleteUserUseCase: FirebaseDeleteUserUseCase) : ProfileBaseUseCase() {

    suspend fun deleteAccount() {
        firebaseDeleteUserUseCase.deleteAccount()
    }
}