package dmitriy.losev.core.core.result

import dmitriy.losev.exception.NULLABLE_EXCEPTION_CODE
import dmitriy.losev.exception.NullableException

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>() {

        override fun getNullableData(): T? {
            return data
        }
    }

    class NullableSuccess<T> : Result<T>() {
        override fun getNullableData(): T? {
            return null
        }
    }
    data class Error(val throwable: Throwable, val extraErrorCode: Int = -1) : Result<Nothing>() {

        override fun getNullableData(): Nothing? {
            return null
        }
    }

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is NullableSuccess<*> -> "NullableSuccess"
            is Error -> "Error[exception=$throwable]"
        }
    }

    abstract fun getNullableData(): R?

    companion object {
        val nullableExceptionResult = Error(throwable = NullableException(), extraErrorCode = NULLABLE_EXCEPTION_CODE)
    }
}
