package dmitriy.losev.auth.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface AuthenticationScreens {

    val name: String

    val route: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    data object StartScreen: AuthenticationScreens {

        override val name = "start_screen"

        override val route = name
    }

    data object LoginScreen: AuthenticationScreens {

        override val name = "login_screen"

        override val route = name
    }

    data object RegistrationScreen: AuthenticationScreens {

        override val name = "registration_screen"

        override val route = name
    }

    data object PasswordScreen: AuthenticationScreens {

        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
        const val PATRONYMIC = "patronymic"
        const val EMAIL = "email"

        override val name = "password_screen"

        override val route = "$name?$FIRST_NAME={$FIRST_NAME}/$LAST_NAME={$LAST_NAME}/$PATRONYMIC={$PATRONYMIC}/$EMAIL/{$EMAIL}"

        override val arguments = listOf(
            navArgument(name = FIRST_NAME) {
                nullable = true
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument(name = LAST_NAME) {
                nullable = true
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument(name = PATRONYMIC) {
                nullable = true
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument(name = EMAIL) {
                type = NavType.StringType
                defaultValue = ""
            }
        )

        fun createRoute(firstName: String?, lastName: String?, patronymic: String?, email: String): String {
            return "$name?${FIRST_NAME}=$firstName/${LAST_NAME}=$lastName/${PATRONYMIC}=$patronymic/$EMAIL/$email"
        }
    }

    data object PasswordResetScreen: AuthenticationScreens {

        const val EMAIL = "email"

        override val name = "password_reset_screen"

        override val route = "$name?$EMAIL={$EMAIL}"

        override val arguments = listOf(
            navArgument(name = EMAIL) {
                nullable = true
                type = NavType.StringType
                defaultValue = ""
            }
        )

        fun createRoute(email: String?): String {
            return "$name?${EMAIL}=$email"
        }
    }
}
