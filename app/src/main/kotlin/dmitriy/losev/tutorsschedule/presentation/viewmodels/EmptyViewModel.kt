package dmitriy.losev.tutorsschedule.presentation.viewmodels

import androidx.navigation.NavController
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.exception.ErrorHandler
import dmitriy.losev.firebase.domain.models.UserData
import dmitriy.losev.tutorsschedule.domain.usecases.FirebaseUseCases
import dmitriy.losev.tutorsschedule.domain.usecases.NavigationUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EmptyViewModel(
    errorHandler: ErrorHandler,
    private val firebaseUseCases: FirebaseUseCases,
    private val navigationUseCases: NavigationUseCases
): BaseViewModel(errorHandler = errorHandler) {

    private val _userData = MutableStateFlow<UserData?>(value = null)

    val userData = _userData.asStateFlow()

    fun getUserData() {
        _userData.value = firebaseUseCases.getUserData()
    }

    fun clearData() {
        _userData.value = null
    }

    fun navigateToAuthenticationActivity(navController: NavController) = processing {
        navigationUseCases.navigateToAuthenticationsScreen(navController = navController)
    }
}