package dmitriy.losev.auth.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import dmitriy.losev.firebase.domain.models.UserDescription
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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

    data object DataScreen: AuthenticationScreens {

        override val name = "data_screen"

        override val route = name
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

    data object PasswordScreen: AuthenticationScreens {

        const val USER_DESCRIPTION = "user_description"

        override val name = "password_screen"

        override val route = "$name/{$USER_DESCRIPTION}"

        override val arguments = listOf(
            navArgument(name = USER_DESCRIPTION) { type = NavType.StringType }
        )

        fun createRoute(userDescription: UserDescription): String {
            return "$name/${Json.encodeToString(userDescription)}"
        }
    }
}
