package dmitriy.losev.profile.presentation.ui.navigation

import androidx.navigation.NamedNavArgument

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

        override val name = "edit_profile_screen"

        override val route = name
    }

    data object ChangePasswordScreen: ProfileScreens {

        override val name = "change_password_screen"

        override val route = name
    }
}
