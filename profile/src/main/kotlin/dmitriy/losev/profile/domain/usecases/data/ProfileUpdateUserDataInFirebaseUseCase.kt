package dmitriy.losev.profile.domain.usecases.data

import android.net.Uri
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateFirstNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdateLastNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdatePatronymicUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseUpdatePhoneNumberUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.domain.usecases.ProfileCheckPhoneNumberUseCase
import dmitriy.losev.profile.domain.usecases.ProfileUpdateAvatarUseCase

class ProfileUpdateUserDataInFirebaseUseCase(
    private val profileUpdateAvatarUseCase: ProfileUpdateAvatarUseCase,
    private val firebaseUpdateFirstNameUseCase: FirebaseUpdateFirstNameUseCase,
    private val firebaseUpdateLastNameUseCase: FirebaseUpdateLastNameUseCase,
    private val firebaseUpdatePatronymicUseCase: FirebaseUpdatePatronymicUseCase,
    private val firebaseUpdatePhoneNumberUseCase: FirebaseUpdatePhoneNumberUseCase,
    private val profileCheckPhoneNumberUseCase: ProfileCheckPhoneNumberUseCase
) : ProfileBaseUseCase() {

    suspend fun updateUserData(
        oldAvatarUri: Uri?,
        newAvatarUri: Uri?,
        firstName: String,
        lastName: String,
        patronymic: String,
        phoneNumber: String
    ) {
        profileCheckPhoneNumberUseCase.checkPhoneNumber(phoneNumber)
        launchFun(
            f1 = { profileUpdateAvatarUseCase.updateAvatar(avatarUri = newAvatarUri, oldAvatarUri = oldAvatarUri) },
            f2 = { firebaseUpdateFirstNameUseCase.updateFirstName(firstName) },
            f3 = { firebaseUpdateLastNameUseCase.updateLastName(lastName) },
            f4 = { firebaseUpdatePatronymicUseCase.updatePatronymic(patronymic) },
            f5 = { firebaseUpdatePhoneNumberUseCase.updatePhoneNumber(phoneNumber) }
        )
    }
}