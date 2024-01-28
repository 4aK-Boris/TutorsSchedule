package dmitriy.losev.profile.domain.usecases.data

import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.profile.domain.models.FullUserData
import dmitriy.losev.profile.domain.models.UserData
import dmitriy.losev.profile.domain.repositories.UserDataRepository

class ProfileGetUserDataFromCacheUseCase(
    private val userDataRepository: UserDataRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): ProfileBaseUseCase() {

    suspend fun getUserData(): UserData? {
        return firebaseGetUserUseCase.getUserWithoutException()?.let { user ->
            userDataRepository.loadUserDataFromCache(userId = user.uid)
        }
    }

    suspend fun getFullUserData(): FullUserData? {
        return firebaseGetUserUseCase.getUserWithoutException()?.let { user ->
            userDataRepository.loadFullUserDataFromCache(userId = user.uid)
        }
    }
}