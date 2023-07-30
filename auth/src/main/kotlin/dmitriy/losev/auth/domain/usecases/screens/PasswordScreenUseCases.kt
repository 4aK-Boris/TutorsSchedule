package dmitriy.losev.auth.domain.usecases.screens

import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseUser
import dmitriy.losev.auth.core.AuthenticationBaseUseCase
import dmitriy.losev.auth.domain.usecases.AuthenticationNavigationUseCases
import dmitriy.losev.auth.domain.usecases.AuthenticationValidateUseCases
import dmitriy.losev.exception.ErrorHandler
import dmitriy.losev.firebase.domain.models.UserDescription
import dmitriy.losev.firebase.domain.usecases.FirebaseAuthUseCases

class PasswordScreenUseCases(
    errorHandler: ErrorHandler,
    private val authenticationValidateUseCases: AuthenticationValidateUseCases,
    private val firebaseAuthUseCases: FirebaseAuthUseCases,
    private val authenticationNavigationUseCases: AuthenticationNavigationUseCases
) : AuthenticationBaseUseCase(errorHandler = errorHandler) {

    suspend fun registration(
        userDescription: UserDescription,
        password1: String,
        password2: String,
        navController: NavController
    ) = safeReturnCall {
        authenticationValidateUseCases.isValidatePassword(
            password1 = password1,
            password2 = password2
        ).processingResult {
            firebaseAuthUseCases.registrationWithEmail(
                email = userDescription.email,
                password = password1
            ).processingResult { result ->
                result.user?.let { user ->
                    updateInfoAndSendEmail(user = user, userDescription = userDescription)
                } ?: throw Exception()
            }.processingResult {
                authenticationNavigationUseCases.navigateToEmptyScreen(navController = navController)
            }
        }
    }

    private suspend fun updateInfoAndSendEmail(
        user: FirebaseUser,
        userDescription: UserDescription
    ) = safeReturnCall(
        call1 = {
            firebaseAuthUseCases.updateInformation(
                user = user,
                userDescription = userDescription
            )
        },
        call2 = { firebaseAuthUseCases.sendEmailVerification(user = user) }
    )
}