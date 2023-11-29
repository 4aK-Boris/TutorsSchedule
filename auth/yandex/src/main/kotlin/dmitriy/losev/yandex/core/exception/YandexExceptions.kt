package dmitriy.losev.yandex.core.exception

import dmitriy.losev.exception.BaseException

class YandexNullTokenException : BaseException(extraErrorCode = YANDEX_NULL_TOKEN_EXCEPTION_CODE)