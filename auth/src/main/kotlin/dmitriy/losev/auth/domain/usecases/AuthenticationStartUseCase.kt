package dmitriy.losev.auth.domain.usecases

import dmitriy.losev.auth.core.AuthenticationBaseUseCase

class AuthenticationStartUseCase(
    private val authenticationCheckAuthUseCase: AuthenticationCheckAuthUseCase,
    private val authenticationCreateDirUseCase: AuthenticationCreateDirUseCase
): AuthenticationBaseUseCase() {

    suspend fun startApp(): Boolean {
        authenticationCreateDirUseCase.createFirs()
        return authenticationCheckAuthUseCase.checkAuth()
    }
}