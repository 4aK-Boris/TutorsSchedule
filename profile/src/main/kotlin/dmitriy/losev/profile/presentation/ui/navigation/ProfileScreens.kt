package dmitriy.losev.profile.presentation.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface ProfileScreens {

    val name: String

    val route: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    data object ProfileScreen: ProfileScreens {

        override val name = "main_profile_screen"

        override val route = name
    }

    data object EditProfileScreen: ProfileScreens {

        const val URI = "uri"

        override val name = "edit_profile_screen"

        override val route = "$name?$URI={$URI}"

        override val arguments = listOf(
            navArgument(name = URI) {
                nullable = true
                type = NavType.StringType
                defaultValue = ""
            }
        )

        fun createRoute(uri: String?): String {
            return "$name?$URI=$uri"
        }
    }

    data object ChangePasswordScreen: ProfileScreens {

        override val name = "change_password_screen"

        override val route = name
    }

    data object ChangeEmailScreen: ProfileScreens {

        override val name = "change_email_screen"

        override val route = name
    }

    data object SettingsScreen: ProfileScreens {

        override val name = "settings_screen"

        override val route = name
    }

    data object CameraScreen: ProfileScreens {

        override val name = "camera_screen"

        override val route = name
    }
}
