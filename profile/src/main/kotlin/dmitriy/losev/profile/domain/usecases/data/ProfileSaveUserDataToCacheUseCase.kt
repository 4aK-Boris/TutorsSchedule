package dmitriy.losev.profile.domain.usecases.data

import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.domain.models.FullUserData
import dmitriy.losev.profile.domain.models.UserData
import dmitriy.losev.profile.domain.repositories.UserDataRepository

class ProfileSaveUserDataToCacheUseCase(
    private val userDataRepository: UserDataRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase,
    private val profileSaveAvatarToCacheUseCase: ProfileSaveAvatarToCacheUseCase
): ProfileBaseUseCase() {

    suspend fun saveUserData(userData: UserData) {
        firebaseGetUserUseCase.getUserWithoutException()?.let { user ->
            userDataRepository.saveUserDataToCache(userId = user.uid, userData = userData)
            profileSaveAvatarToCacheUseCase.saveAvatar(avatarUri = userData.avatarUri)
        }
    }

    suspend fun saveFullUserData(fullUserData: FullUserData) {
        firebaseGetUserUseCase.getUserWithoutException()?.let { user ->
            userDataRepository.saveFullUserDataToCache(userId = user.uid, fullUserData = fullUserData)
            profileSaveAvatarToCacheUseCase.saveAvatar(avatarUri = fullUserData.avatarUri)
        }
    }
}