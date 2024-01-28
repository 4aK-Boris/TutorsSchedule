package dmitriy.losev.profile.domain.usecases.data

import dmitriy.losev.core.cache.CacheLoader
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.domain.models.FullUserData
import dmitriy.losev.profile.domain.models.UserData

class ProfileGetUserDataUseCase(
    private val profileGetUserDataFromCacheUseCase: ProfileGetUserDataFromCacheUseCase,
    private val profileGetUserDataFromFirebaseUseCase: ProfileGetUserDataFromFirebaseUseCase,
    private val profileSaveUserDataToCacheUseCase: ProfileSaveUserDataToCacheUseCase
) : ProfileBaseUseCase(), CacheLoader {

    suspend fun getUserData(onCacheLoading: (UserData?) -> Unit, onFirebaseLoading: (UserData) -> Unit) {
        loadData(
            loadFromCache = profileGetUserDataFromCacheUseCase::getUserData,
            loadFromFirebase = profileGetUserDataFromFirebaseUseCase::getUserData,
            saveToCache = profileSaveUserDataToCacheUseCase::saveUserData,
            onCacheLoading = onCacheLoading,
            onFirebaseLoading = onFirebaseLoading
        )
    }

    suspend fun getFullUserData(onCacheLoading: (FullUserData?) -> Unit, onFirebaseLoading: (FullUserData) -> Unit) {
        loadData(
            loadFromCache = profileGetUserDataFromCacheUseCase::getFullUserData,
            loadFromFirebase = profileGetUserDataFromFirebaseUseCase::getFullUserData,
            saveToCache = profileSaveUserDataToCacheUseCase::saveFullUserData,
            onCacheLoading = onCacheLoading,
            onFirebaseLoading = onFirebaseLoading
        )
    }
}