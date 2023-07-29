package dmitriy.losev.tutorsschedule.core

import dmitriy.losev.core.core.BaseUseCase
import dmitriy.losev.core.exception.ErrorHandler

abstract class AppBaseUseCase(errorHandler: ErrorHandler): BaseUseCase(errorHandler = errorHandler)