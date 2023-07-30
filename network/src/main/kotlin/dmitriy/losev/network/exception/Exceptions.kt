package dmitriy.losev.network.exception

class BadRequest : NetworkException(extraErrorCode = BAD_REQUEST)

class InternalServerError : NetworkException(extraErrorCode = INTERNAL_SERVER_ERROR)

class NoInternet : NetworkException(extraErrorCode = NO_INTERNET)

class UnknownNetworkException : NetworkException(extraErrorCode = NETWORK_CALL_FAIL)

class NotFound : NetworkException(extraErrorCode = NOT_FOUND)
