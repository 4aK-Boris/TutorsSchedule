package dmitriy.losev.tutorsschedule.core

import dmitriy.losev.core.core.BaseUseCase
import dmitriy.losev.core.core.ErrorHandler

abstract class AppBaseUseCase(errorHandler: ErrorHandler): BaseUseCase(errorHandler = errorHandler)