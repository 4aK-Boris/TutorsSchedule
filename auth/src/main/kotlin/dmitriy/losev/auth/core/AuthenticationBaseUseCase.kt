package dmitriy.losev.auth.core

import dmitriy.losev.core.core.BaseUseCase
import dmitriy.losev.core.core.ErrorHandler

abstract class AuthenticationBaseUseCase(errorHandler: ErrorHandler) : BaseUseCase(errorHandler = errorHandler)
