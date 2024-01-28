package dmitriy.losev.auth.presentation.viewmodels

import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.auth.domain.usecases.AuthenticationStartUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnMain

class StartScreenViewModel(
    private val authenticationNavigationUseCases: AuthenticationNavigationUseCases,
    private val authenticationStartUseCase: AuthenticationStartUseCase
) : BaseViewModel() {

    fun navigateToLoginScreen(authenticationNavigationListener: AuthenticationNavigationListener) = runOnMain {
        safeCall { authenticationNavigationUseCases.navigateToLoginScreen(authenticationNavigationListener) }.processing()
    }

    fun navigateToRegistrationScreen(authenticationNavigationListener: AuthenticationNavigationListener) = runOnMain {
        safeCall { authenticationNavigationUseCases.navigateToRegistrationScreen(authenticationNavigationListener) }.processing()
    }

    fun checkAuth(authenticationNavigationListener: AuthenticationNavigationListener) = runOnIO {
        safeCall { authenticationStartUseCase.startApp() }.processing { isAuth ->
            if (isAuth) {
                authenticationNavigationUseCases.navigateToProfileScreen(authenticationNavigationListener)
            }
        }
    }
}
