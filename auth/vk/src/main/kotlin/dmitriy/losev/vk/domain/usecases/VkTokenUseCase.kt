package dmitriy.losev.vk.domain.usecases

import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.domain.models.FirebaseToken
import dmitriy.losev.vk.core.VkBaseUseCase
import dmitriy.losev.vk.domain.models.VkUserData
import dmitriy.losev.vk.domain.repositories.VkTokenRepository

class VkTokenUseCase(
    errorHandler: ErrorHandler,
    private val vkTokenRepository: VkTokenRepository
) : VkBaseUseCase(errorHandler) {

    suspend fun getToken(vkUserData: VkUserData): Result<FirebaseToken> = safeCall {
        vkTokenRepository.getFirebaseToken(vkUserData)
    }
}