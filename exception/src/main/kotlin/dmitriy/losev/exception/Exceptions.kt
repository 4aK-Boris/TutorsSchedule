package dmitriy.losev.exception

class SafeCallFail : BaseException(extraErrorCode = SAFE_CALL_FAIL)

class ResultMapException: BaseException(extraErrorCode = RESULT_MAP_EXCEPTION_CODE)
