package dmitriy.losev.profile.domain.usecases.data

import dmitriy.losev.firebase.domain.usecases.user.FirebaseEmailVerifiedUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetAvatarUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetEmailUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetFirstNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetLastNameUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetPatronymicUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.domain.models.FullUserData
import dmitriy.losev.profile.domain.models.UserData
import dmitriy.losev.profile.domain.usecases.ProfileEmailAndPasswordChangedUseCase
import dmitriy.losev.profile.domain.usecases.ProfileGetNameUseCase
import dmitriy.losev.profile.domain.usecases.ProfileGetPhoneNumberUseCase

class ProfileGetUserDataFromFirebaseUseCase(
    private val firebaseGetAvatarUseCase: FirebaseGetAvatarUseCase,
    private val profileGetNameUseCase: ProfileGetNameUseCase,
    private val profileGetPhoneNumberUseCase: ProfileGetPhoneNumberUseCase,
    private val firebaseGetFirstNameUseCase: FirebaseGetFirstNameUseCase,
    private val firebaseGetLastNameUseCase: FirebaseGetLastNameUseCase,
    private val firebaseGetPatronymicUseCase: FirebaseGetPatronymicUseCase,
    private val firebaseGetEmailUseCase: FirebaseGetEmailUseCase,
    private val profileEmailAndPasswordChangedUseCase: ProfileEmailAndPasswordChangedUseCase,
    private val firebaseEmailVerifiedUseCase: FirebaseEmailVerifiedUseCase
) : ProfileBaseUseCase() {

    suspend fun getUserData(): UserData = launchFourFunWithFourNullable(
        f1 = { firebaseGetAvatarUseCase.getAvatarUri() },
        f2 = { profileGetNameUseCase.getDisplayName() },
        f3 = { profileGetPhoneNumberUseCase.getFormattedPhoneNumber() },
        f4 = { firebaseGetEmailUseCase.getEmail() }
    ).let { (avatarUri, name, phoneNumber, email) ->
        UserData(avatarUri, name, email, phoneNumber)
    }

    suspend fun getFullUserData(): FullUserData = launchNineFunWithFourNullable(
        f1 = { firebaseGetAvatarUseCase.getAvatarUri() },
        f2 = { profileGetNameUseCase.getDisplayName() },
        f3 = { profileGetPhoneNumberUseCase.getPhoneNumber() },
        f4 = { firebaseGetEmailUseCase.getEmail() },
        f5 = { firebaseGetFirstNameUseCase.getFirstName() },
        f6 = { firebaseGetLastNameUseCase.getLastName() },
        f7 = { firebaseGetPatronymicUseCase.getPatronymic() },
        f8 = { profileEmailAndPasswordChangedUseCase.hasChangedEmailAndPassword() },
        f9 = { firebaseEmailVerifiedUseCase.isEmailVerified() }
    ).let { (avatarUri, name, phoneNumber, email, firstName, lastName, patronymic, hasEmailAndPasswordChanged, isEmailVerified) ->
        FullUserData(avatarUri, name, firstName, lastName, patronymic, email, phoneNumber, hasEmailAndPasswordChanged, isEmailVerified)
    }
}