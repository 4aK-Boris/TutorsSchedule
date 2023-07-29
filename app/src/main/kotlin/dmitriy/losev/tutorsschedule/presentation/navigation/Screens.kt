package dmitriy.losev.tutorsschedule.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface Screens {

    val name: String

    val route: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    object AuthenticationScreen: Screens {

        override val name = "authentication"

        override val route = name
    }

    object EmptyScreen: Screens {

        override val name = "empty"

        override val route = name
    }

    object FileScreen: Screens {

        const val PATH = "path"

        const val STORAGE = "storage"

        override val name = "file"

        override val route = "$name?$PATH={$PATH}/{$STORAGE}"

        override val arguments = listOf(
            navArgument(name = PATH) {
                type = NavType.StringType
                                     } ,
            navArgument(name = STORAGE) { type = NavType.StringType }
        )

        fun createRoute(storage: String, path: String): String {
            return "$name?$PATH=$path/$storage"
        }
    }

    object ChooseFileScreen: Screens {

        const val PATH = "path"

        const val STORAGE = "storage"

        override val name = "chooseFile"

        override val route = "$name?$PATH={$PATH}/{$STORAGE}"

        override val arguments = listOf(
            navArgument(name = PATH) {
                type = NavType.StringType
            } ,
            navArgument(name = STORAGE) { type = NavType.StringType }
        )

        fun createRoute(storage: String, path: String): String {
            return "$name?$PATH=$path/$storage"
        }
    }
}
