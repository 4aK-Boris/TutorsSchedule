package dmitriy.losev.profile.domain.usecases.data

import android.net.Uri
import dmitriy.losev.core.cache.CacheLoader
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileUpdateUserDataUseCase(
    private val profileUpdateUserDataInFirebaseUseCase: ProfileUpdateUserDataInFirebaseUseCase,
    private val profileUpdateUserDataInCacheUseCase: ProfileUpdateUserDataInCacheUseCase
) : ProfileBaseUseCase(), CacheLoader {

    suspend fun updateUserData(
        oldAvatarUri: Uri?,
        newAvatarUri: Uri?,
        firstName: String,
        lastName: String,
        patronymic: String,
        phoneNumber: String
    ) {
        updateData(
            updateInFirebase = {
                profileUpdateUserDataInFirebaseUseCase.updateUserData(
                    oldAvatarUri,
                    newAvatarUri,
                    firstName,
                    lastName,
                    patronymic,
                    phoneNumber
                )
            },
            updateInCache = profileUpdateUserDataInCacheUseCase::updateUserData
        )
    }
}