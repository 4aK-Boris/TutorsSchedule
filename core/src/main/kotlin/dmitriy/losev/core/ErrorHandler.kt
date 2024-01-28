package dmitriy.losev.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ErrorHandler(private val resourcesManager: ResourcesManager) {

    private val scope = CoroutineScope(context = Dispatchers.Default)

    private val _errorMessage: MutableStateFlow<String> = MutableStateFlow(value = "")
    private val _errorState: MutableStateFlow<Boolean> = MutableStateFlow(value = false)

    val errorMessage: StateFlow<String> = _errorMessage
    val errorState: StateFlow<Boolean> = _errorState

    private var job: Job? = null

    fun close() {
        _errorState.value = false
    }

    private fun showError(message: String) = scope.launch {
        _errorState.value = true
        _errorMessage.value = message
        delay(3000L)
        close()
    }

    private val errorsMap = mutableMapOf<Int, () -> Job>()
    private val errorsFun = mutableMapOf<Int, () -> Unit>()

    fun setErrorMap(errorsMap: Map<Int, Int>) {
        this.errorsMap.putAll(convert(errorsMap = errorsMap))
    }

    fun setErrorFun(errorsFun: Map<Int, () -> Unit>) {
        this.errorsFun.putAll(errorsFun)
    }

    private fun convert(errorsMap: Map<Int, Int>): Iterable<Pair<Int, () -> Job>> {
        return errorsMap.map { (key, id) -> key to { showError(message = resourcesManager.getString(id)) } }
    }

    fun handleError(errorId: Int) {
        job?.cancel()
        errorsFun[errorId]?.invoke()
        job = errorsMap[errorId]?.invoke() ?: showError("Неизвестная ошибка: $errorId")
    }
}