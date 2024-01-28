package dmitriy.losev.profile.core.exception

import dmitriy.losev.exception.BaseException

internal class UserAbsenceException: BaseException(extraErrorCode = USER_ABSENCE_EXCEPTION_CODE)
internal class UserAvailableException: BaseException(extraErrorCode = USER_AVAILABLE_EXCEPTION_CODE)

internal class EmptyPasswordException : BaseException(extraErrorCode = EMPTY_PASSWORD_EXCEPTION_CODE)
internal class EmptyOldPasswordException : BaseException(extraErrorCode = EMPTY_OLD_PASSWORD_EXCEPTION_CODE)
internal class EmptyNewPasswordException : BaseException(extraErrorCode = EMPTY_NEW_PASSWORD_EXCEPTION_CODE)
internal class MinLengthPasswordException : BaseException(extraErrorCode = MIN_LENGTH_PASSWORD_EXCEPTION_CODE)
internal class MaxLengthPasswordException : BaseException(extraErrorCode = MAX_LENGTH_PASSWORD_EXCEPTION_CODE)
internal class PasswordLowerCaseLetterException : BaseException(extraErrorCode = PASSWORD_LOWER_CASE_LETTER_EXCEPTION_CODE)
internal class PasswordUpperCaseLetterException : BaseException(extraErrorCode = PASSWORD_UPPER_CASE_LETTER_EXCEPTION_CODE)
internal class DifferentPasswordsException : BaseException(extraErrorCode = DIFFERENT_PASSWORDS_EXCEPTION_CODE)
internal class InvalidPhoneNumberException: BaseException(extraErrorCode = INVALID_PHONE_NUMBER_EXCEPTION_CODE)
internal class SimilarOldAndNewPasswordException: BaseException(extraErrorCode = SIMILAR_OLD_AND_NEW_PASSWORDS_EXCEPTION_CODE)

internal class EmptyEmailException: BaseException(extraErrorCode = EMPTY_EMAIL_EXCEPTION_CODE)
internal class EmptyOldEmailException: BaseException(extraErrorCode = EMPTY_OLD_EMAIL_EXCEPTION_CODE)
internal class EmptyNewEmailException: BaseException(extraErrorCode = EMPTY_NEW_EMAIL_EXCEPTION_CODE)
internal class EmailValidationException: BaseException(extraErrorCode = EMAIL_VALIDATION_EXCEPTION_CODE)
internal class OldEmailValidationException: BaseException(extraErrorCode = OLD_EMAIL_VALIDATION_EXCEPTION_CODE)
internal class NewEmailValidationException: BaseException(extraErrorCode = NEW_EMAIL_VALIDATION_EXCEPTION_CODE)

