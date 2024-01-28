package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.firebase.domain.usecases.auth.FirebaseEmailAuthUseCase
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseLogOutUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseChangePasswordUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetEmailUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.core.exception.EmptyEmailException

class ProfileChangePasswordUseCase(
    private val firebaseChangePasswordUseCase: FirebaseChangePasswordUseCase,
    private val profileCheckPasswordUseCase: ProfileCheckPasswordUseCase,
    private val firebaseGetEmailUseCase: FirebaseGetEmailUseCase,
    private val firebaseLogOutUseCase: FirebaseLogOutUseCase,
    private val firebaseEmailAuthUseCase: FirebaseEmailAuthUseCase
) : ProfileBaseUseCase() {

    suspend fun changePassword(oldPassword: String, newPassword1: String, newPassword2: String) {
        profileCheckPasswordUseCase.checkPassword(oldPassword, newPassword1, newPassword2)
        val email = firebaseGetEmailUseCase.getEmail() ?: throw EmptyEmailException()
        firebaseLogOutUseCase.logOut()
        firebaseEmailAuthUseCase.authWithEmail(email, oldPassword)
        firebaseChangePasswordUseCase.changePassword(newPassword1)
    }
}