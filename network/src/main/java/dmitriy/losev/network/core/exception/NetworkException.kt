package dmitriy.losev.network.core.exception

import dmitriy.losev.exception.BaseException

open class NetworkException(extraErrorCode: Int) : BaseException(extraErrorCode = extraErrorCode)

