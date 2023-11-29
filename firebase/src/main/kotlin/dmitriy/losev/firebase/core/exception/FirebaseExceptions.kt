package dmitriy.losev.firebase.core.exception

import dmitriy.losev.exception.BaseException

class GoogleAuthIsNotSuccessException : BaseException(extraErrorCode = GOOGLE_AUTH_IS_NOT_SUCCESS_EXCEPTION_CODE)
class EmptyUserData : BaseException(extraErrorCode = EMPTY_USER_DATA)
class UploadAvatarException: BaseException(extraErrorCode = UPLOAD_AVATAR_EXCEPTION_CODE)
class TokenAuthenticationException: BaseException(extraErrorCode = TOKEN_AUTHENTICATION_EXCEPTION_CODE)
class DisplayNameIsNullException: BaseException(extraErrorCode = DISPLAY_NAME_IS_NULL_EXCEPTION_CODE)
class AvatarIsNullException: BaseException(extraErrorCode = AVATAR_URI_IS_NULL_EXCEPTION_CODE)

class EmailIsNullException: BaseException(extraErrorCode = EMAIL_IS_NULL_EXCEPTION_CODE)
class UserNotAuthorizationException: BaseException(extraErrorCode = USER_NOT_AUTHORIZATION_EXCEPTION_CODE)
class NoProvidersException: BaseException(extraErrorCode = NO_PROVIDERS_EXCEPTION_CODE)

class NullableStudentException: BaseException(extraErrorCode = NULLABLE_STUDENT_EXCEPTION_CODE)