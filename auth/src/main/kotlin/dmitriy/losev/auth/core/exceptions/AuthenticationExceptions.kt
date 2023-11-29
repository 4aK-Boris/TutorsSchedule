package dmitriy.losev.auth.core.exceptions

import dmitriy.losev.exception.BaseException

internal class EmptyPasswordException : BaseException(extraErrorCode = EMPTY_PASSWORD_EXCEPTION_CODE)
internal class MinLengthPasswordException : BaseException(extraErrorCode = MIN_LENGTH_PASSWORD_EXCEPTION_CODE)
internal class MaxLengthPasswordException : BaseException(extraErrorCode = MAX_LENGTH_PASSWORD_EXCEPTION_CODE)
internal class PasswordLowerCaseLetterException : BaseException(extraErrorCode = PASSWORD_LOWER_CASE_LETTER_EXCEPTION_CODE)
internal class PasswordUpperCaseLetterException : BaseException(extraErrorCode = PASSWORD_UPPER_CASE_LETTER_EXCEPTION_CODE)
internal class DifferentPasswordsException : BaseException(extraErrorCode = DIFFERENT_PASSWORDS_EXCEPTION_CODE)
internal class UserNotFoundException : BaseException(extraErrorCode = USER_NOT_FOUND)
internal class EmailAuthIsNotSuccess : BaseException(extraErrorCode = EMAIL_AUTH_IS_NOT_SUCCESS)
internal class AnonymousAuthIsNotSuccess : BaseException(extraErrorCode = ANONYMOUS_AUTH_IS_NOT_SUCCESS)
internal class GoogleAuthIsNotSuccessException : BaseException(extraErrorCode = GOOGLE_AUTH_IS_NOT_SUCCESS_EXCEPTION_CODE)
internal class EmptyUserData : BaseException(extraErrorCode = EMPTY_USER_DATA)
internal class EmptyEmailException : BaseException(extraErrorCode = EMPTY_EMAIL_EXCEPTION_CODE)
internal class EmailValidationException : BaseException(extraErrorCode = EMAIL_VALIDATION_EXCEPTION_CODE)
internal class NoUserException : BaseException(extraErrorCode = NO_USER_EXCEPTION_CODE)
internal class HasUserException : BaseException(extraErrorCode = HAS_USER_EXCEPTION_CODE)
internal class UpdateUserDataException: BaseException(extraErrorCode = UPDATE_USER_DATE_EXCEPTION_CODE)
internal class UserAvailableException: BaseException(extraErrorCode = USER_AVAILABLE_EXCEPTION_CODE)
internal class UserAbsenceException: BaseException(extraErrorCode = USER_ABSENCE_EXCEPTION_CODE)