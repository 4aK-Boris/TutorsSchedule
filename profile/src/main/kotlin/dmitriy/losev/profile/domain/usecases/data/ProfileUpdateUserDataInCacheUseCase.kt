package dmitriy.losev.profile.domain.usecases.data

import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileUpdateUserDataInCacheUseCase(
    private val profileGetUserDataFromFirebaseUseCase: ProfileGetUserDataFromFirebaseUseCase,
    private val profileSaveUserDataToCacheUseCase: ProfileSaveUserDataToCacheUseCase
) : ProfileBaseUseCase() {

    suspend fun updateUserData() {
        val userData = profileGetUserDataFromFirebaseUseCase.getUserData()
        val fullUserData = profileGetUserDataFromFirebaseUseCase.getFullUserData()
        profileSaveUserDataToCacheUseCase.saveUserData(userData)
        profileSaveUserDataToCacheUseCase.saveFullUserData(fullUserData)
    }
}