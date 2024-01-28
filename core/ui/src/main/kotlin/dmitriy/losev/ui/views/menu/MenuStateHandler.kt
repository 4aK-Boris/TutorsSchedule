package dmitriy.losev.ui.views.menu

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MenuStateHandler {

    private val _menuState = MutableStateFlow(value = false)

    val menuState = _menuState.asStateFlow()

    fun openMenu() {
        _menuState.value = true
    }

    fun closeMenu() {
        _menuState.value = false
    }

    fun onMenuStateChanged(menuState: Boolean) {
        _menuState.value = menuState
    }
}