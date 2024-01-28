package dmitriy.losev.profile.data.repositories

import dmitriy.losev.core.cache.CacheDataHandler
import dmitriy.losev.profile.data.dto.FullUserDataDTO
import dmitriy.losev.profile.data.dto.UserDataDTO
import dmitriy.losev.profile.data.mappers.FullUserDataMapper
import dmitriy.losev.profile.data.mappers.UserDataMapper
import dmitriy.losev.profile.domain.models.FullUserData
import dmitriy.losev.profile.domain.models.UserData
import dmitriy.losev.profile.domain.repositories.UserDataRepository

class UserDataRepositoryImpl(
    private val userDataMapper: UserDataMapper,
    private val fullUserDataMapper: FullUserDataMapper,
    private val cacheDataHandler: CacheDataHandler
): UserDataRepository {

    private val userDataSerializer = UserDataDTO.serializer()
    private val fullUserDataSerializer = FullUserDataDTO.serializer()

    override suspend fun loadUserDataFromCache(userId: String): UserData? {
        val userDataDTO = cacheDataHandler.loadData(name = "$USER_DATA_NAME$userId", deserializer = userDataSerializer)
        return userDataMapper.map(value = userDataDTO)
    }

    override suspend fun saveUserDataToCache(userId: String, userData: UserData) {
        val userDataDTO = userDataMapper.map(value = userData)
        cacheDataHandler.saveData(name = "$USER_DATA_NAME$userId", data = userDataDTO, serializer = userDataSerializer)
    }

    override suspend fun loadFullUserDataFromCache(userId: String): FullUserData? {
        val fullUserDataDTO = cacheDataHandler.loadData(name = "$FULL_USER_DATA_NAME$userId", deserializer = fullUserDataSerializer)
        return fullUserDataMapper.map(value = fullUserDataDTO)
    }

    override suspend fun saveFullUserDataToCache(userId: String, fullUserData: FullUserData) {
        val fullUserDataDTO = fullUserDataMapper.map(value = fullUserData)
        cacheDataHandler.saveData(name = "$FULL_USER_DATA_NAME$userId", data = fullUserDataDTO, serializer = fullUserDataSerializer)
    }

    companion object {
        private const val USER_DATA_NAME = "user_data_"
        private const val FULL_USER_DATA_NAME = "full_user_data_"
    }
}