package dmitriy.losev.core.result

sealed class NullableResult<out R> {

    class Success<T>(val data: T?) : NullableResult<T>()

    data class Error(val throwable: Throwable, val extraErrorCode: Int = -1) : NullableResult<Nothing>()
}
