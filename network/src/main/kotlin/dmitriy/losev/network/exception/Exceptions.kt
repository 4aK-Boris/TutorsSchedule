package dmitriy.losev.network.exception

import dmitriy.losev.exception.BaseException

class BadRequest : NetworkException(extraErrorCode = BAD_REQUEST_EXCEPTION_CODE)

class InternalServerError : NetworkException(extraErrorCode = INTERNAL_SERVER_ERROR)

class NoInternetException : NetworkException(extraErrorCode = NO_INTERNET_EXCEPTION_CODE)

class UnknownNetworkException : NetworkException(extraErrorCode = NETWORK_CALL_FAIL)

class NotFound : NetworkException(extraErrorCode = NOT_FOUND)

class ConnectionException: NetworkException(extraErrorCode = CONNECTION_EXCEPTION_CODE)

class TimeoutException: BaseException(extraErrorCode = TIMEOUT_EXCEPTION_CODE)
