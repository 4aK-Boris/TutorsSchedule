package dmitriy.losev.vk.core

import dmitriy.losev.core.core.BaseUseCase
import dmitriy.losev.core.core.ErrorHandler

abstract class VkBaseUseCase(errorHandler: ErrorHandler) : BaseUseCase(errorHandler = errorHandler)
