package dmitriy.losev.students.presentation.viewmodels

import dmitriy.losev.students.core.ScreenState
import dmitriy.losev.ui.core.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainStudentsScreenViewModel: BaseViewModel() {

    private val _screenState = MutableStateFlow(value = ScreenState.STUDENTS)

    val screenState = _screenState.asStateFlow()

    fun onScreenStateChanged(screenState: ScreenState) {
        _screenState.value = screenState
    }
}