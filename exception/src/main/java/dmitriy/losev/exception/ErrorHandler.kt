package dmitriy.losev.exception

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ErrorHandler {

    private val _errorMessage: MutableStateFlow<String> = MutableStateFlow(value = "")
    private val _errorState: MutableStateFlow<Boolean> = MutableStateFlow(value = false)

    val errorMessage: StateFlow<String> = _errorMessage
    val errorState: StateFlow<Boolean> = _errorState

    fun close() {
        _errorState.value = false
    }

    private fun showError(message: String) {
        _errorState.value = true
        _errorMessage.value = message
    }

    private val errorsMap = mutableMapOf<Int, () -> Unit>()

    fun setErrorActionsMap(errorsMap: Map<Int, String>) {
        this.errorsMap.putAll(convert(errorsMap = errorsMap))
    }

    private fun convert(errorsMap: Map<Int, String>): Iterable<Pair<Int, () -> Unit>> {
        return errorsMap.map { (key, value) -> key to { showError(message = value) } }
    }

    fun handleError(errorId: Int) {
        errorsMap[errorId]?.invoke() ?: showError("Неизвестная ошибка: $errorId")
    }
}