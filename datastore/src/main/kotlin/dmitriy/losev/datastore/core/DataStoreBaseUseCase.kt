package dmitriy.losev.datastore.core

import dmitriy.losev.core.core.BaseUseCase
import dmitriy.losev.core.core.ErrorHandler

abstract class DataStoreBaseUseCase(errorHandler: ErrorHandler) : BaseUseCase(errorHandler)