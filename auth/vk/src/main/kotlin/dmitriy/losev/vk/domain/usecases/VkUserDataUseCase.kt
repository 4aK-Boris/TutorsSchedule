package dmitriy.losev.vk.domain.usecases

import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.vk.core.VkBaseUseCase
import dmitriy.losev.vk.domain.models.VkUserData
import dmitriy.losev.vk.domain.repositories.VkDataRepository

class VkUserDataUseCase(
    errorHandler: ErrorHandler,
    private val vkUserDataRepository: VkDataRepository
) : VkBaseUseCase(errorHandler) {

    suspend fun getVkUserData(userId: Long, token: String, email: String?): Result<VkUserData> = safeCall {
        vkUserDataRepository.getVkUserData(userId, token, email)
    }
}