package dmitriy.losev.network.exception

import dmitriy.losev.exception.BaseException

open class NetworkException(extraErrorCode: Int) : BaseException(extraErrorCode = extraErrorCode)

