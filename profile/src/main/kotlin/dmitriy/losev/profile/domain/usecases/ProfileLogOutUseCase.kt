package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.firebase.domain.usecases.auth.FirebaseLogOutUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileLogOutUseCase(private val firebaseLogOutUseCase: FirebaseLogOutUseCase, ) : ProfileBaseUseCase() {

    fun logOut() {
        firebaseLogOutUseCase.logOut()
    }
}