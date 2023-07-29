package dmitriy.losev.core.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.core.exception.ErrorHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(private val errorHandler: ErrorHandler) : ViewModel() {

    open val errorMap: Map<Int, String> = emptyMap()

    init {
        errorHandler.setErrorActionsMap(errorsMap = errorMap)
    }

    val errorMessage: StateFlow<String> = errorHandler.errorMessage
    val errorState: StateFlow<Boolean> = errorHandler.errorState

    val close = errorHandler::close

    fun <T: Any> processing(
        errorVisible: Boolean = true,
        exceptionsCode: List<Int> = emptyList(),
        dispatcher: CoroutineDispatcher = Dispatchers.Default,
        onSuccess: suspend (T) -> Unit = {},
        onError: suspend () -> Unit = {},
        call: suspend () -> Result<T>
    ) {
        viewModelScope.launch(context = dispatcher) {
            call().let { result ->
                when (result) {
                    is Result.Success -> onSuccess(result.data)
                    is Result.Error -> {
                        onError()
                        if (result.extraErrorCode !in exceptionsCode && errorVisible) {
                            errorHandler.handleError(result.extraErrorCode)
                        }
                    }
                }
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
}
