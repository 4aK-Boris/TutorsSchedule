package dmitriy.losev.ui.core

import androidx.lifecycle.ViewModel
import dmitriy.losev.core.ErrorHandler
import dmitriy.losev.core.result.NullableResult
import dmitriy.losev.core.result.Result
import dmitriy.losev.exception.BaseException
import dmitriy.losev.ui.usecases.GetThemeStateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel : ViewModel(), KoinComponent {

    private val themeStateUseCase by inject<GetThemeStateUseCase>()
    private val errorHandler by inject<ErrorHandler>()

    open val errorMap: Map<Int, Int> = emptyMap()

    open val errorFun: Map<Int, () -> Unit> = emptyMap()

    private val _isLoading = MutableStateFlow(value = false)

    val isLoading = _isLoading.asStateFlow()

    init {
        errorHandler.setErrorMap(errorsMap = errorMap)
        errorHandler.setErrorFun(errorsFun = errorFun)
    }

    val errorMessage: StateFlow<String> = errorHandler.errorMessage
    val errorState: StateFlow<Boolean> = errorHandler.errorState

    var themeState = flowOf(value = false)

    val close = errorHandler::close

    fun loadThemeState() = runOnIO {
        safeCall { themeStateUseCase.getThemeState() }.processing { themeFlow ->
            themeState = themeFlow
        }
    }

    fun startLoading() {
        _isLoading.value = true
    }

    fun stopLoading() {
        _isLoading.value = false
    }

    fun onIsLoadingChanged(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    suspend fun <T : Any> safeCall(call: suspend () -> T): Result<T> {
        return try {
            Result.Success(data = call())
        } catch (e: BaseException) {
            e.printStackTrace()
            Result.Error(throwable = e, extraErrorCode = e.extraErrorCode)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(throwable = e)
        }
    }

    suspend fun <T : Any> safeNullableCall(call: suspend () -> T?): NullableResult<T> {
        return try {
            NullableResult.Success(data = call())
        } catch (e: BaseException) {
            e.printStackTrace()
            NullableResult.Error(throwable = e, extraErrorCode = e.extraErrorCode)
        } catch (e: Exception) {
            e.printStackTrace()
            NullableResult.Error(throwable = e)
        }
    }

    fun <T> Result<T>.processing() {
        if (this is Result.Error) {
            errorHandler.handleError(errorId = extraErrorCode)
        }
    }

    fun <T> NullableResult<T>.processing() {
        if (this is NullableResult.Error) {
            errorHandler.handleError(errorId = extraErrorCode)
        }
    }

    suspend fun <T> Result<T>.processing(
        onError: suspend () -> Unit,
        onSuccess: suspend (T) -> Unit
    ) {
        when (this) {
            is Result.Success -> onSuccess(data)
            is Result.Error -> {
                onError()
                errorHandler.handleError(errorId = extraErrorCode)
            }
        }
    }

    suspend fun <T> NullableResult<T>.processing(
        onError: suspend () -> Unit,
        onSuccess: suspend (T?) -> Unit
    ) {
        when (this) {
            is NullableResult.Success -> onSuccess(data)
            is NullableResult.Error -> {
                onError()
                errorHandler.handleError(errorId = extraErrorCode)
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
            is Result.Error -> {
                onError()
                if (this.extraErrorCode !in exceptionsCode && errorVisible) {
                    errorHandler.handleError(errorId = this.extraErrorCode)
                }
            }
        }
    }
    suspend fun <T> Result<T>.processingLoading(
        errorVisible: Boolean = true,
        exceptionsCode: List<Int> = emptyList(),
        onError: suspend () -> Unit = { },
        onSuccess: suspend (T) -> Unit = { }
    ) {
        when (this) {
            is Result.Success -> {
                stopLoading()
                onSuccess(data)
            }
            is Result.Error -> {
                stopLoading()
                onError()
                if (this.extraErrorCode !in exceptionsCode && errorVisible) {
                    errorHandler.handleError(errorId = this.extraErrorCode)
                }
            }
        }
    }

    suspend fun <T> Result<T>.processingLoading(
        exceptionsErrorCode: List<Int> = emptyList(),
        onError: suspend () -> Unit = { },
        onSuccess: suspend (T) -> Unit = { }
    ) {
        when (this) {
            is Result.Success -> {
                stopLoading()
                onSuccess(data)
            }
            is Result.Error -> {
                stopLoading()
                if (this.extraErrorCode in exceptionsErrorCode) {
                    onError()
                }
                errorHandler.handleError(errorId = this.extraErrorCode)
            }
        }
    }


    suspend fun <T> NullableResult<T>.processing(
        errorVisible: Boolean = true,
        exceptionsCode: List<Int> = emptyList(),
        onError: suspend () -> Unit = { },
        onSuccess: suspend (T?) -> Unit = { }
    ) {
        when (this) {
            is NullableResult.Success -> onSuccess(data)
            is NullableResult.Error -> {
                onError()
                if (this.extraErrorCode !in exceptionsCode && errorVisible) {
                    errorHandler.handleError(errorId = this.extraErrorCode)
                }
            }
        }
    }
}
