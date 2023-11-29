package dmitriy.losev.core.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ErrorHandler(private val resourcesManager: ResourcesManager) {

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

    fun setErrorActionsMap(errorsMap: Map<Int, Int>) {
        this.errorsMap.putAll(convert(errorsMap = errorsMap))
    }

    private fun convert(errorsMap: Map<Int, Int>): Iterable<Pair<Int, () -> Unit>> {
        return errorsMap.map { (key, id) -> key to { showError(message = resourcesManager.getString(id)) } }
    }

    fun handleError(errorId: Int) {
        errorsMap[errorId]?.invoke() ?: showError("Неизвестная ошибка: $errorId")
    }
}