package dmitriy.losev.yandex.core.exception

import dmitriy.losev.exception.BaseException

class YandexNullTokenException : BaseException(extraErrorCode = YANDEX_NULL_TOKEN_EXCEPTION_CODE)

class YandexDecodeTokenException: BaseException(extraErrorCode = YANDEX_DECODE_TOKEN_EXCEPTION_CODE)

class YandexVerifyTokenException: BaseException(extraErrorCode = YANDEX_VERIFY_TOKEN_EXCEPTION_CODE)