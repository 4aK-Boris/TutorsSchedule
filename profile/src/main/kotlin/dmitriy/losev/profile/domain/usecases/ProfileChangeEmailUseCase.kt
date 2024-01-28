package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.firebase.domain.usecases.auth.FirebaseEmailAuthUseCase
import dmitriy.losev.firebase.domain.usecases.auth.FirebaseLogOutUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateEmailUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileChangeEmailUseCase(
    private val firebaseEmailAuthUseCase: FirebaseEmailAuthUseCase,
    private val firebaseLogOutUseCase: FirebaseLogOutUseCase,
    private val firebaseUpdateEmailUseCase: FirebaseUpdateEmailUseCase,
    private val profileCheckEmailUseCase: ProfileCheckEmailUseCase,
    private val profileCheckPasswordUseCase: ProfileCheckPasswordUseCase
): ProfileBaseUseCase() {

    suspend fun changeEmail(oldEmail: String, newEmail: String, password: String) {
        profileCheckEmailUseCase.checkOldEmailForChange(oldEmail)
        profileCheckEmailUseCase.checkNewEmailForChange(newEmail)
        profileCheckPasswordUseCase.checkPassword(password)
        firebaseLogOutUseCase.logOut()
        firebaseEmailAuthUseCase.authWithEmail(email = oldEmail, password = password)
        firebaseUpdateEmailUseCase.updateEmail(email = newEmail)
    }
}