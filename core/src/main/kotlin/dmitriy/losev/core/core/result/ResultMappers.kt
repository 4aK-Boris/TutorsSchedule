package dmitriy.losev.core.core.result

import dmitriy.losev.exception.RESULT_MAP_EXCEPTION_CODE
import dmitriy.losev.exception.ResultMapException

val resultMapException =
    Result.Error(throwable = ResultMapException(), extraErrorCode = RESULT_MAP_EXCEPTION_CODE)

val emptyResult = Result.Success(data = Unit)

inline fun <reified T: Any> T.successResult(): Result<T> = Result.Success(data = this)

inline fun <reified T : Any, reified R : Any> mapResult(
    result1: Result<T>,
    result2: Result<R>
): Result<Output2<T, R>> {
    return when {
        result1 is Result.Success && result2 is Result.Success -> Result.Success(
            data = Output2(
                result1 = result1.data,
                result2 = result2.data
            )
        )

        result1 is Result.Error -> result1
        result2 is Result.Error -> result2
        else -> resultMapException
    }
}

inline fun <reified T : Any, reified R : Any, reified K : Any> mapResult(
    result1: Result<T>,
    result2: Result<R>,
    result3: Result<K>
): Result<Output3<T, R, K>> {
    return when {
        result1 is Result.Success && result2 is Result.Success && result3 is Result.Success -> Result.Success(
            data = Output3(
                result1 = result1.data,
                result2 = result2.data,
                result3 = result3.data
            )
        )

        result1 is Result.Error -> result1
        result2 is Result.Error -> result2
        result3 is Result.Error -> result3
        else -> resultMapException
    }
}

inline fun <reified T : Any, reified R : Any, reified K : Any, reified S : Any> mapResult(
    result1: Result<T>,
    result2: Result<R>,
    result3: Result<K>,
    result4: Result<S>
): Result<Output4<T, R, K, S>> {
    return when {
        result1 is Result.Success && result2 is Result.Success && result3 is Result.Success && result4 is Result.Success -> Result.Success(
            data = Output4(
                result1 = result1.data,
                result2 = result2.data,
                result3 = result3.data,
                result4 = result4.data
            )
        )

        result1 is Result.Error -> result1
        result2 is Result.Error -> result2
        result3 is Result.Error -> result3
        result4 is Result.Error -> result4
        else -> resultMapException
    }
}

inline fun <reified T : Any, reified R : Any, reified K : Any, reified S : Any> mapResultWithThreeNullable(
    result1: Result<T>,
    result2: Result<R>,
    result3: Result<K>,
    result4: Result<S>
): Result<Output4WithThreeNullable<T, R, K, S>> {
    return when {
        result1 !is Result.Error && result2 !is Result.Error && result3 !is Result.Error && result4 is Result.Success -> Result.Success(
            data = Output4WithThreeNullable(
                result1 = result1.getNullableData(),
                result2 = result2.getNullableData(),
                result3 = result3.getNullableData(),
                result4 = result4.data
            )
        )

        result1 is Result.Error -> result1
        result2 is Result.Error -> result2
        result3 is Result.Error -> result3
        result4 is Result.Error -> result4
        result4 is Result.NullableSuccess -> Result.nullableExceptionResult
        else -> resultMapException
    }
}

inline fun <reified T : Any, reified R : Any, reified K : Any, reified S : Any, reified D : Any> mapResult(
    result1: Result<T>,
    result2: Result<R>,
    result3: Result<K>,
    result4: Result<S>,
    result5: Result<D>
): Result<Output5<T, R, K, S, D>> {
    return when {
        result1 is Result.Success && result2 is Result.Success && result3 is Result.Success && result4 is Result.Success && result5 is Result.Success -> Result.Success(
            data = Output5(
                result1 = result1.data,
                result2 = result2.data,
                result3 = result3.data,
                result4 = result4.data,
                result5 = result5.data
            )
        )

        result1 is Result.Error -> result1
        result2 is Result.Error -> result2
        result3 is Result.Error -> result3
        result4 is Result.Error -> result4
        result5 is Result.Error -> result5
        else -> resultMapException
    }
}

inline fun <reified T : Any, reified R : Any, reified K : Any, reified S : Any, reified D : Any> mapResultWithFourNullable(
    result1: Result<T>,
    result2: Result<R>,
    result3: Result<K>,
    result4: Result<S>,
    result5: Result<D>
): Result<Output5WithFourNullable<T, R, K, S, D>> {
    return when {
        result1 !is Result.Error && result2 !is Result.Error && result3 !is Result.Error && result4 !is Result.Error && result5 is Result.Success -> Result.Success(
            data = Output5WithFourNullable(
                result1 = result1.getNullableData(),
                result2 = result2.getNullableData(),
                result3 = result3.getNullableData(),
                result4 = result4.getNullableData(),
                result5 = result5.data
            )
        )
        result1 is Result.Error -> result1
        result2 is Result.Error -> result2
        result3 is Result.Error -> result3
        result4 is Result.Error -> result4
        result5 is Result.Error -> result5
        result5 is Result.NullableSuccess -> Result.nullableExceptionResult
        else -> resultMapException
    }
}

inline fun <reified T : Any, reified R : Any, reified K : Any, reified S : Any, reified D : Any, F: Any> mapResult(
    result1: Result<T>,
    result2: Result<R>,
    result3: Result<K>,
    result4: Result<S>,
    result5: Result<D>,
    result6: Result<F>
): Result<Output6<T, R, K, S, D, F>> {
    return when {
        result1 is Result.Success && result2 is Result.Success && result3 is Result.Success && result4 is Result.Success && result5 is Result.Success && result6 is Result.Success -> Result.Success(
            data = Output6(
                result1 = result1.data,
                result2 = result2.data,
                result3 = result3.data,
                result4 = result4.data,
                result5 = result5.data,
                result6 = result6.data
            )
        )

        result1 is Result.Error -> result1
        result2 is Result.Error -> result2
        result3 is Result.Error -> result3
        result4 is Result.Error -> result4
        result5 is Result.Error -> result5
        result6 is Result.Error -> result6
        else -> resultMapException
    }
}

inline fun <reified T : Any, reified R : Any, reified K : Any, reified S : Any, reified D : Any, F: Any> mapResultWithFiveNullable(
    result1: Result<T>,
    result2: Result<R>,
    result3: Result<K>,
    result4: Result<S>,
    result5: Result<D>,
    result6: Result<F>
): Result<Output6WithFiveNullable<T, R, K, S, D, F>> {
    return when {
        result1 !is Result.Error && result2 !is Result.Error && result3 !is Result.Error && result4 !is Result.Error && result5 !is Result.Error && result6 is Result.Success -> Result.Success(
            data = Output6WithFiveNullable(
                result1 = result1.getNullableData(),
                result2 = result2.getNullableData(),
                result3 = result3.getNullableData(),
                result4 = result4.getNullableData(),
                result5 = result5.getNullableData(),
                result6 = result6.data
            )
        )

        result1 is Result.Error -> result1
        result2 is Result.Error -> result2
        result3 is Result.Error -> result3
        result4 is Result.Error -> result4
        result5 is Result.Error -> result5
        result6 is Result.Error -> result6
        result6 is Result.NullableSuccess -> Result.nullableExceptionResult
        else -> resultMapException
    }
}