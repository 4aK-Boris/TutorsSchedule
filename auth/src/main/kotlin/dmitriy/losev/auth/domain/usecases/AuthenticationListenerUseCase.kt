package dmitriy.losev.auth.domain.usecases

import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.core.AuthenticationNavigationListener
import dmitriy.losev.core.core.AuthenticationListener
import dmitriy.losev.core.core.ErrorHandler

class AuthenticationListenerUseCase(errorHandler: ErrorHandler) : AuthenticationBaseUseCase(errorHandler) {

    fun getAuthenticationListener(
        authenticationNavigationListener: AuthenticationNavigationListener,
        onIsLoadingChanged: (Boolean) -> Unit
    ): AuthenticationListener {

        return object : AuthenticationListener {

            override suspend fun onSuccess() {
                onIsLoadingChanged(false)
                authenticationNavigationListener.navigateToProfileScreen()
            }

            override suspend fun onError() {
                onIsLoadingChanged(false)
            }

            override suspend fun onLoading() {
                onIsLoadingChanged(true)
            }
        }
    }
}