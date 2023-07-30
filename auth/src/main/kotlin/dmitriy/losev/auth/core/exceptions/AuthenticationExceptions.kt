package dmitriy.losev.auth.core.exceptions

import dmitriy.losev.exception.BaseException

class EmptyPasswordException : BaseException(extraErrorCode = EMPTY_PASSWORD_EXCEPTION_CODE)

class MinLengthPasswordException :
    BaseException(extraErrorCode = MIN_LENGTH_PASSWORD_EXCEPTION_CODE)

class MaxLengthPasswordException :
    BaseException(extraErrorCode = MAX_LENGTH_PASSWORD_EXCEPTION_CODE)

class LowerCaseLetterException : BaseException(extraErrorCode = PASSWORD_LOWER_CASE_LETTER_EXCEPTION_CODE)

class UpperCaseLetterException : BaseException(extraErrorCode = PASSWORD_UPPER_CASE_LETTER_EXCEPTION_CODE)

class DifferentPasswordsException : BaseException(extraErrorCode = DIFFERENT_PASSWORDS_EXCEPTION_CODE)

class UserNotFoundException : BaseException(extraErrorCode = USER_NOT_FOUND)

class EmailAuthIsNotSuccess : BaseException(extraErrorCode = EMAIL_AUTH_IS_NOT_SUCCESS)
class AnonymousAuthIsNotSuccess : BaseException(extraErrorCode = ANONYMOUS_AUTH_IS_NOT_SUCCESS)
class GoogleAuthIsNotSuccess : BaseException(extraErrorCode = GOOGLE_AUTH_IS_NOT_SUCCESS)
class EmptyUserData : BaseException(extraErrorCode = EMPTY_USER_DATA)

class MaxLengthFirstNameException : BaseException(extraErrorCode = MAX_LENGTH_FIRST_NAME_EXCEPTION_CODE)

class MaxLengthLastNameException : BaseException(extraErrorCode = MAX_LENGTH_LAST_NAME_EXCEPTION_CODE)

class EmptyEmailException : BaseException(extraErrorCode = EMPTY_EMAIL_EXCEPTION_CODE)

class InvalidEmailException : BaseException(extraErrorCode = INVALID_EMAIL_EXCEPTION_CODE)

class UpdateUserDataException: BaseException(extraErrorCode = UPDATE_USER_DATE_EXCEPTION_CODE)