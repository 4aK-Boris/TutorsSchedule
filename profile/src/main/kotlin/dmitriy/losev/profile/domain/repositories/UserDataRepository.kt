package dmitriy.losev.profile.domain.repositories

import dmitriy.losev.profile.domain.models.FullUserData
import dmitriy.losev.profile.domain.models.UserData

interface UserDataRepository {

    suspend fun loadUserDataFromCache(userId: String): UserData?

    suspend fun saveUserDataToCache(userId: String, userData: UserData)

    suspend fun loadFullUserDataFromCache(userId: String): FullUserData?

    suspend fun saveFullUserDataToCache(userId: String, fullUserData: FullUserData)
}