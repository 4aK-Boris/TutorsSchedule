package dmitriy.losev.subjects.core.exception

import dmitriy.losev.exception.BaseException

internal class NameEmptyException: BaseException(extraErrorCode = NAME_EMPTY_EXCEPTION_CODE)
internal class SubjectIsNotLoadingException: BaseException(extraErrorCode = SUBJECT_IS_NOT_LOADING_EXCEPTION_CODE)

