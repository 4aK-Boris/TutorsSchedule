package dmitriy.losev.profile.core.exception

import dmitriy.losev.exception.BaseException

internal class UserAvailableException: BaseException(extraErrorCode = USER_AVAILABLE_EXCEPTION_CODE)
internal class EmptyEmailException: BaseException(extraErrorCode = EMPTY_EMAIL_EXCEPTION_CODE)
internal class EmailValidationException: BaseException(extraErrorCode = EMAIL_VALIDATION_EXCEPTION_CODE)
internal class EmptyPasswordException : BaseException(extraErrorCode = EMPTY_PASSWORD_EXCEPTION_CODE)
internal class MinLengthPasswordException : BaseException(extraErrorCode = MIN_LENGTH_PASSWORD_EXCEPTION_CODE)
internal class MaxLengthPasswordException : BaseException(extraErrorCode = MAX_LENGTH_PASSWORD_EXCEPTION_CODE)
internal class PasswordLowerCaseLetterException : BaseException(extraErrorCode = PASSWORD_LOWER_CASE_LETTER_EXCEPTION_CODE)
internal class PasswordUpperCaseLetterException : BaseException(extraErrorCode = PASSWORD_UPPER_CASE_LETTER_EXCEPTION_CODE)
internal class DifferentPasswordsException : BaseException(extraErrorCode = DIFFERENT_PASSWORDS_EXCEPTION_CODE)