package dmitriy.losev.core.core

import dmitriy.losev.core.core.result.Output2
import dmitriy.losev.core.core.result.Output3
import dmitriy.losev.core.core.result.Output4
import dmitriy.losev.core.core.result.Output4WithThreeNullable
import dmitriy.losev.core.core.result.Output5
import dmitriy.losev.core.core.result.Output5WithFourNullable
import dmitriy.losev.core.core.result.Output6
import dmitriy.losev.core.core.result.Output6WithFiveNullable
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.core.core.result.emptyResult
import dmitriy.losev.core.core.result.mapResult
import dmitriy.losev.core.core.result.mapResultWithFiveNullable
import dmitriy.losev.core.core.result.mapResultWithFourNullable
import dmitriy.losev.core.core.result.mapResultWithThreeNullable
import dmitriy.losev.exception.BaseException
import dmitriy.losev.exception.SAFE_CALL_FAIL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.reflect.KClass

abstract class BaseUseCase(private val errorHandler: ErrorHandler) {

    open val exceptions: Map<KClass<out Throwable>, Int> = emptyMap()

    suspend inline fun <reified T : Any> safeCall(crossinline call: suspend CoroutineScope.() -> T): Result<T> =
        safeReturnCall {
            Result.Success(call())
        }

    suspend inline fun <reified T : Any> safeCallNullable(crossinline call: suspend CoroutineScope.() -> T?): Result<T> =
        safeReturnCall {
            call()?.let { Result.Success(it) } ?:  Result.NullableSuccess()
        }

    suspend inline fun <reified T: Any> safeCallUnit(crossinline call: suspend CoroutineScope.() -> T): Result<Unit> =
        safeReturnCall {
            call()
            Result.Success(data = Unit)
        }

    suspend inline fun <reified T : Any> safeReturnCall(crossinline call: suspend CoroutineScope.() -> Result<T>): Result<T> =
        safeReturnCall(exceptions = exceptions, call = call)

    suspend inline fun <reified T : Any> safeReturnCallUnit(crossinline call: suspend CoroutineScope.() -> Result<T>): Result<Unit> {
        safeReturnCall(exceptions = exceptions, call = call)
        return Result.Success(data = Unit)
    }

    suspend inline fun <reified T : Any> safeReturnCall(
        exceptions: Map<KClass<out Throwable>, Int>,
        crossinline call: suspend CoroutineScope.() -> Result<T>
    ): Result<T> = try {
        coroutineScope {
            call()
        }
    } catch (baseException: BaseException) {
        baseException.printStackTrace()
        Result.Error(baseException, baseException.extraErrorCode)
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        Result.Error(
            throwable = throwable,
            extraErrorCode = exceptions[throwable::class] ?: SAFE_CALL_FAIL
        )
    }

    suspend inline fun <reified T : Any, reified R : Any> safeReturnCall(
        crossinline call1: suspend CoroutineScope.() -> Result<T>,
        crossinline call2: suspend CoroutineScope.() -> Result<R>
    ): Result<Output2<T, R>> = safeReturnCall {
        val async1 = async { call1() }
        val async2 = async { call2() }
        mapResult(result1 = async1.await(), result2 = async2.await())
    }

    suspend inline fun safeReturnUnitCall(
        crossinline call1: suspend CoroutineScope.() -> Result<Unit>,
        crossinline call2: suspend CoroutineScope.() -> Result<Unit>
    ): Result<Unit> = safeReturnCall {
        val async1 = async { call1() }
        val async2 = async { call2() }
        async1.await()
        async2.await()
        emptyResult
    }

    suspend inline fun <reified T : Any, reified R : Any, reified K : Any> safeReturnCall(
        crossinline call1: suspend CoroutineScope.() -> Result<T>,
        crossinline call2: suspend CoroutineScope.() -> Result<R>,
        crossinline call3: suspend CoroutineScope.() -> Result<K>
    ): Result<Output3<T, R, K>> = safeReturnCall(exceptions = exceptions) {
        val async1 = async { call1() }
        val async2 = async { call2() }
        val async3 = async { call3() }
        mapResult(result1 = async1.await(), result2 = async2.await(), result3 = async3.await())
    }

    suspend inline fun safeReturnUnitCall(
        crossinline call1: suspend CoroutineScope.() -> Result<Unit>,
        crossinline call2: suspend CoroutineScope.() -> Result<Unit>,
        crossinline call3: suspend CoroutineScope.() -> Result<Unit>
    ): Result<Unit> = safeReturnCall(exceptions = exceptions) {
        val async1 = async { call1() }
        val async2 = async { call2() }
        val async3 = async { call3() }
        async1.await()
        async2.await()
        async3.await()
        emptyResult
    }

    suspend inline fun <reified T : Any, reified R : Any, reified K : Any, reified S : Any> safeReturnCall(
        crossinline call1: suspend CoroutineScope.() -> Result<T>,
        crossinline call2: suspend CoroutineScope.() -> Result<R>,
        crossinline call3: suspend CoroutineScope.() -> Result<K>,
        crossinline call4: suspend CoroutineScope.() -> Result<S>
    ): Result<Output4<T, R, K, S>> {
        return safeReturnCall(exceptions = exceptions) {
            val async1 = async { call1() }
            val async2 = async { call2() }
            val async3 = async { call3() }
            val async4 = async { call4() }
            mapResult(
                result1 = async1.await(),
                result2 = async2.await(),
                result3 = async3.await(),
                result4 = async4.await()
            )
        }
    }

    suspend inline fun <reified T : Any, reified R : Any, reified K : Any, reified S : Any> safeReturnCallWithThreeNullable(
        crossinline call1: suspend CoroutineScope.() -> Result<T>,
        crossinline call2: suspend CoroutineScope.() -> Result<R>,
        crossinline call3: suspend CoroutineScope.() -> Result<K>,
        crossinline call4: suspend CoroutineScope.() -> Result<S>
    ): Result<Output4WithThreeNullable<T, R, K, S>> {
        return safeReturnCall(exceptions = exceptions) {
            val async1 = async { call1() }
            val async2 = async { call2() }
            val async3 = async { call3() }
            val async4 = async { call4() }
            mapResultWithThreeNullable(
                result1 = async1.await(),
                result2 = async2.await(),
                result3 = async3.await(),
                result4 = async4.await()
            )
        }
    }

    suspend inline fun <reified T : Any, reified R : Any, reified K : Any, reified S : Any, reified D : Any> safeReturnCall(
        crossinline call1: suspend CoroutineScope.() -> Result<T>,
        crossinline call2: suspend CoroutineScope.() -> Result<R>,
        crossinline call3: suspend CoroutineScope.() -> Result<K>,
        crossinline call4: suspend CoroutineScope.() -> Result<S>,
        crossinline call5: suspend CoroutineScope.() -> Result<D>
    ): Result<Output5<T, R, K, S, D>> {
        return safeReturnCall(exceptions = exceptions) {
            val async1 = async { call1() }
            val async2 = async { call2() }
            val async3 = async { call3() }
            val async4 = async { call4() }
            val async5 = async { call5() }
            mapResult(
                result1 = async1.await(),
                result2 = async2.await(),
                result3 = async3.await(),
                result4 = async4.await(),
                result5 = async5.await()
            )
        }
    }

    suspend inline fun <reified T : Any, reified R : Any, reified K : Any, reified S : Any, reified D : Any> safeReturnCallWithFourNullable(
        crossinline call1: suspend CoroutineScope.() -> Result<T>,
        crossinline call2: suspend CoroutineScope.() -> Result<R>,
        crossinline call3: suspend CoroutineScope.() -> Result<K>,
        crossinline call4: suspend CoroutineScope.() -> Result<S>,
        crossinline call5: suspend CoroutineScope.() -> Result<D>
    ): Result<Output5WithFourNullable<T, R, K, S, D>> {
        return safeReturnCall(exceptions = exceptions) {
            val async1 = async { call1() }
            val async2 = async { call2() }
            val async3 = async { call3() }
            val async4 = async { call4() }
            val async5 = async { call5() }
            mapResultWithFourNullable(
                result1 = async1.await(),
                result2 = async2.await(),
                result3 = async3.await(),
                result4 = async4.await(),
                result5 = async5.await()
            )
        }
    }

    suspend inline fun <reified T : Any, reified R : Any, reified K : Any, reified S : Any, reified D : Any, reified F: Any> safeReturnCall(
        crossinline call1: suspend CoroutineScope.() -> Result<T>,
        crossinline call2: suspend CoroutineScope.() -> Result<R>,
        crossinline call3: suspend CoroutineScope.() -> Result<K>,
        crossinline call4: suspend CoroutineScope.() -> Result<S>,
        crossinline call5: suspend CoroutineScope.() -> Result<D>,
        crossinline call6: suspend CoroutineScope.() -> Result<F>
    ): Result<Output6<T, R, K, S, D, F>> {
        return safeReturnCall(exceptions = exceptions) {
            val async1 = async { call1() }
            val async2 = async { call2() }
            val async3 = async { call3() }
            val async4 = async { call4() }
            val async5 = async { call5() }
            val async6 = async { call6() }
            mapResult(
                result1 = async1.await(),
                result2 = async2.await(),
                result3 = async3.await(),
                result4 = async4.await(),
                result5 = async5.await(),
                result6 = async6.await()
            )
        }
    }

    suspend inline fun <reified T : Any, reified R : Any, reified K : Any, reified S : Any, reified D : Any, reified F: Any> safeReturnCallWithFiveNullable(
        crossinline call1: suspend CoroutineScope.() -> Result<T>,
        crossinline call2: suspend CoroutineScope.() -> Result<R>,
        crossinline call3: suspend CoroutineScope.() -> Result<K>,
        crossinline call4: suspend CoroutineScope.() -> Result<S>,
        crossinline call5: suspend CoroutineScope.() -> Result<D>,
        crossinline call6: suspend CoroutineScope.() -> Result<F>
    ): Result<Output6WithFiveNullable<T, R, K, S, D, F>> {
        return safeReturnCall(exceptions = exceptions) {
            val async1 = async { call1() }
            val async2 = async { call2() }
            val async3 = async { call3() }
            val async4 = async { call4() }
            val async5 = async { call5() }
            val async6 = async { call6() }
            mapResultWithFiveNullable(
                result1 = async1.await(),
                result2 = async2.await(),
                result3 = async3.await(),
                result4 = async4.await(),
                result5 = async5.await(),
                result6 = async6.await()
            )
        }
    }

    suspend inline fun <reified T : Any, reified R : Any> safeCall(
        crossinline call1: suspend () -> T,
        crossinline call2: suspend () -> R
    ): Result<Output2<T, R>> {
        return safeReturnCall(
            call1 = { Result.Success(call1()) }, call2 = { Result.Success(call2()) }
        )
    }

    suspend inline fun <reified T : Any, reified R : Any, reified K : Any> safeCall(
        crossinline call1: suspend CoroutineScope.() -> T,
        crossinline call2: suspend CoroutineScope.() -> R,
        crossinline call3: suspend CoroutineScope.() -> K
    ): Result<Output3<T, R, K>> {
        return safeReturnCall(
            call1 = { Result.Success(data = call1()) },
            call2 = { Result.Success(data = call2()) },
            call3 = { Result.Success(data = call3()) }
        )
    }

    suspend inline fun <reified T : Any, reified R : Any, reified K : Any, reified S : Any> safeCall(
        crossinline call1: suspend CoroutineScope.() -> T,
        crossinline call2: suspend CoroutineScope.() -> R,
        crossinline call3: suspend CoroutineScope.() -> K,
        crossinline call4: suspend CoroutineScope.() -> S
    ): Result<Output4<T, R, K, S>> {
        return safeReturnCall(
            call1 = { Result.Success(data = call1()) },
            call2 = { Result.Success(data = call2()) },
            call3 = { Result.Success(data = call3()) },
            call4 = { Result.Success(data = call4()) },
        )
    }

    suspend inline fun <reified T : Any, reified R : Any, reified K : Any, reified S : Any, reified D : Any> safeCall(
        crossinline call1: suspend CoroutineScope.() -> T,
        crossinline call2: suspend CoroutineScope.() -> R,
        crossinline call3: suspend CoroutineScope.() -> K,
        crossinline call4: suspend CoroutineScope.() -> S,
        crossinline call5: suspend CoroutineScope.() -> D
    ): Result<Output5<T, R, K, S, D>> {
        return safeReturnCall(
            call1 = { Result.Success(data = call1()) },
            call2 = { Result.Success(data = call2()) },
            call3 = { Result.Success(data = call3()) },
            call4 = { Result.Success(data = call4()) },
            call5 = { Result.Success(data = call5()) },
        )
    }

    suspend inline fun <reified T : Any, reified R : Any, reified K : Any, reified S : Any, reified D : Any, reified F: Any> safeCall(
        crossinline call1: suspend CoroutineScope.() -> T,
        crossinline call2: suspend CoroutineScope.() -> R,
        crossinline call3: suspend CoroutineScope.() -> K,
        crossinline call4: suspend CoroutineScope.() -> S,
        crossinline call5: suspend CoroutineScope.() -> D,
        crossinline call6: suspend CoroutineScope.() -> F
    ): Result<Output6<T, R, K, S, D, F>> {
        return safeReturnCall(
            call1 = { Result.Success(data = call1()) },
            call2 = { Result.Success(data = call2()) },
            call3 = { Result.Success(data = call3()) },
            call4 = { Result.Success(data = call4()) },
            call5 = { Result.Success(data = call5()) },
            call6 = { Result.Success(data = call6()) }
        )
    }

    suspend fun <T, R> Result<T>.processingResult(
        onError: suspend () -> Unit = { },
        onSuccess: suspend (T) -> Result<R>
    ): Result<R> {
        return when (this) {
            is Result.Success -> onSuccess(data)
            is Result.NullableSuccess -> {
                onError()
                Result.nullableExceptionResult
            }
            is Result.Error -> {
                onError()
                this
            }
        }
    }

    suspend fun <T, R> Result<T>.processingNullableResult(
        onError: suspend () -> Unit = { },
        onSuccess: suspend (T?) -> Result<R>
    ): Result<R> {
        return when (this) {
            is Result.Success -> onSuccess(data)
            is Result.NullableSuccess -> onSuccess(null)
            is Result.Error -> {
                onError()
                this
            }
        }
    }

//    suspend fun <T, R> Result<T>.processingResult(
//        errorVisible: Boolean = true,
//        exceptionsCode: List<Int> = emptyList(),
//        onError: suspend () -> Unit = { },
//        onSuccess: suspend (T) -> Result<R>
//    ): Result<R> {
//       return when (this) {
//            is Result.Success -> onSuccess(data)
//            is Result.Error -> {
//                onError()
//                if (this.extraErrorCode !in exceptionsCode && errorVisible) {
//                    errorHandler.handleError(errorId = this.extraErrorCode)
//                }
//                this
//            }
//        }
//    }

    suspend fun <T, R> Result<T>.processingResult(
        errorVisible: Boolean = true,
        exceptionsCode: List<Int> = emptyList(),
        onError: suspend () -> Result<R>,
        onSuccess: suspend (T) -> Result<R>
    ): Result<R> {
        return when (this) {
            is Result.Success -> onSuccess(data)
            is Result.NullableSuccess -> {
                onError()
                Result.nullableExceptionResult
            }
            is Result.Error -> {
                if (this.extraErrorCode !in exceptionsCode && errorVisible) {
                    errorHandler.handleError(errorId = this.extraErrorCode)
                }
                onError()
            }
        }
    }

    suspend fun <T, R> Result<T>.processingNullableResult(
        errorVisible: Boolean = true,
        exceptionsCode: List<Int> = emptyList(),
        onError: suspend () -> Result<R>,
        onSuccess: suspend (T?) -> Result<R>
    ): Result<R> {
        return when (this) {
            is Result.Success, is Result.NullableSuccess -> onSuccess(getNullableData())
            is Result.Error -> {
                if (this.extraErrorCode !in exceptionsCode && errorVisible) {
                    errorHandler.handleError(errorId = this.extraErrorCode)
                }
                onError()
            }
        }
    }

    suspend fun <T> Result<T>.processingResultUnit(
        onError: suspend () -> Unit = { },
        onSuccess: suspend (T) -> Unit
    ): Result<Unit> {
        return when (this) {
            is Result.Success -> {
                onSuccess(data)
                Result.Success(data = Unit)
            }
            is Result.NullableSuccess -> {
                onError()
                Result.nullableExceptionResult
            }
            is Result.Error -> {
                onError()
                this
            }
        }
    }

    suspend fun <T> Result<T>.processing(
        errorVisible: Boolean = true,
        exceptionsCode: List<Int> = emptyList(),
        onError: suspend () -> Unit = { },
        onSuccess: suspend (T) -> Unit = { }
    ) {
        when (this) {
            is Result.Success -> onSuccess(data)
            is Result.NullableSuccess -> {
                onError()
                Result.nullableExceptionResult
            }
            is Result.Error -> {
                onError()
                if (this.extraErrorCode !in exceptionsCode && errorVisible) {
                    errorHandler.handleError(errorId = this.extraErrorCode)
                }
            }
        }
    }

    suspend fun <T> Result<T>.processingNullable(
        errorVisible: Boolean = true,
        exceptionsCode: List<Int> = emptyList(),
        onError: suspend () -> Unit = { },
        onSuccess: suspend (T?) -> Unit = { }
    ) {
        when (this) {
            is Result.Success, is Result.NullableSuccess -> onSuccess(getNullableData())
            is Result.Error -> {
                onError()
                if (this.extraErrorCode !in exceptionsCode && errorVisible) {
                    errorHandler.handleError(errorId = this.extraErrorCode)
                }
            }
        }
    }
}
