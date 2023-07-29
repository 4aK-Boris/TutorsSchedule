package dmitriy.losev.firebase.core.exception

import dmitriy.losev.core.exception.BaseException

class GoogleAuthIsNotSuccess : BaseException(extraErrorCode = GOOGLE_AUTH_IS_NOT_SUCCESS)
class EmptyUserData : BaseException(extraErrorCode = EMPTY_USER_DATA)


class UploadAvatarException: BaseException(extraErrorCode = UPLOAD_AVATAR_EXCEPTION_CODE)